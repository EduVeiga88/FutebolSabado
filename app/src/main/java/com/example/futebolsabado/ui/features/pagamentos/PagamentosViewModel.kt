package com.example.futebolsabado.ui.features.pagamentos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futebolsabado.domain.repository.PaymentRepository
import com.example.futebolsabado.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagamentosViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    var uiState by mutableStateOf(PagamentosUiState(isLoading = true))
        private set

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val year = uiState.selectedYear
                val players = playerRepository.getAll().first()
                val payments = paymentRepository.getPaymentsByYear(year)

                val paidMonthsByPlayer = payments
                    .groupBy { it.playerId }
                    .mapValues { entry ->
                        entry.value.map { it.month }.toSet()
                    }

                uiState = uiState.copy(
                    isLoading = false,
                    players = players,
                    paidMonthsByPlayer = paidMonthsByPlayer
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Erro ao carregar pagamentos"
                )
            }
        }
    }

    fun onToggleMonth(playerId: Int, month: Int) {
        val currentMap = uiState.paidMonthsByPlayer.toMutableMap()
        val currentMonths = currentMap[playerId].orEmpty().toMutableSet()

        if (month in currentMonths) {
            currentMonths.remove(month)
        } else {
            currentMonths.add(month)
        }

        currentMap[playerId] = currentMonths

        uiState = uiState.copy(
            paidMonthsByPlayer = currentMap
        )
    }

    fun onSave() {
        viewModelScope.launch {
            try {
                val year = uiState.selectedYear
                val players = uiState.players
                val currentUiMap = uiState.paidMonthsByPlayer
                val existingPayments = paymentRepository.getPaymentsByYear(year)

                val existingMap = existingPayments
                    .groupBy { it.playerId }
                    .mapValues { entry -> entry.value.map { it.month }.toSet() }

                players.forEach { player ->
                    val oldMonths = existingMap[player.id].orEmpty()
                    val newMonths = currentUiMap[player.id].orEmpty()

                    val monthsToAdd = newMonths - oldMonths
                    val monthsToRemove = oldMonths - newMonths

                    monthsToAdd.forEach { month ->
                        paymentRepository.addPayment(player.id, month, year)
                    }

                    monthsToRemove.forEach { month ->
                        paymentRepository.removePayment(player.id, month, year)
                    }
                }

                loadData()
            } catch (e: Exception) {
                uiState = uiState.copy(
                    errorMessage = e.message ?: "Erro ao guardar pagamentos"
                )
            }
        }
    }

    fun onYearChange(year: Int) {
        uiState = uiState.copy(selectedYear = year)
        loadData()
    }
}