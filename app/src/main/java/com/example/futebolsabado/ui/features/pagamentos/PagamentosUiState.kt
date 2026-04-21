package com.example.futebolsabado.ui.features.pagamentos

import com.example.futebolsabado.domain.model.Player

data class PagamentosUiState(
    val isLoading: Boolean = false,
    val players: List<Player> = emptyList(),
    val paidMonthsByPlayer: Map<Int, Set<Int>> = emptyMap(),
    val selectedYear: Int = 2026,
    val errorMessage: String? = null
) {
    val canSave: Boolean
        get() = players.isNotEmpty()
}