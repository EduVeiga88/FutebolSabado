package com.example.futebolsabado.ui.features.addMatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futebolsabado.domain.model.CreateMatchRequest
import com.example.futebolsabado.domain.model.TeamPickFase
import com.example.futebolsabado.domain.repository.MatchRepository
import com.example.futebolsabado.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMatchViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddMatchUiState())
    val uiState: StateFlow<AddMatchUiState> = _uiState.asStateFlow()

    init {
        loadPlayers()
    }

    fun onEvent(event: AddMatchEvent) {
        when (event) {
            is AddMatchEvent.PlayerClicked -> onPlayerClicked(event.playerId)
            is AddMatchEvent.IncrementGoal -> changeGoal(event.playerId, delta = +1)
            is AddMatchEvent.DecrementGoal -> changeGoal(event.playerId, delta = -1)
            AddMatchEvent.SaveMatch -> saveMatch()
            AddMatchEvent.ClearError -> _uiState.update { it.copy(error = null) }
            AddMatchEvent.ClearSaved -> _uiState.update { it.copy(savedMatchId = null) }
        }
    }

    private fun loadPlayers() {
        viewModelScope.launch {
            playerRepository.getAll()
                .collect { players ->
                    _uiState.update {
                        it.copy(players = players)
                    }
                }
        }
    }


    private fun onPlayerClicked(playerId: Int) {
        _uiState.update { state ->
            when (state.phase) {

                TeamPickFase.COLETES -> {
                    if (playerId in state.coletes) {
                        state.copy(coletes = state.coletes - playerId)
                    } else {
                        if (playerId in state.semColetes) return@update state

                        // limite 5
                        if (state.coletes.size >= 5) return@update state

                        val newColetes = state.coletes + playerId
                        val newPhase = if (newColetes.size == 5) TeamPickFase.SEMCOLETES else state.phase
                        state.copy(coletes = newColetes, phase = newPhase)
                    }
                }

                TeamPickFase.SEMCOLETES -> {
                    if (playerId in state.semColetes) {
                        state.copy(semColetes = state.semColetes - playerId)
                    } else {
                        if (playerId in state.coletes) return@update state
                        if (state.semColetes.size >= 5) return@update state

                        val newSem = state.semColetes + playerId
                        val newPhase = if (newSem.size == 5) TeamPickFase.DONE else state.phase
                        state.copy(semColetes = newSem, phase = newPhase)
                    }
                }

                TeamPickFase.DONE -> state
            }
        }
    }

    private fun changeGoal(playerId: Int, delta: Int) {
        _uiState.update { state ->
            val isInAnyTeam = (playerId in state.coletes) || (playerId in state.semColetes)
            if (!isInAnyTeam) return@update state

            val current = state.goalsByPlayer[playerId] ?: 0
            val next = (current + delta).coerceAtLeast(0)

            state.copy(goalsByPlayer = state.goalsByPlayer + (playerId to next))
        }
    }

    private fun saveMatch() {
        val state = _uiState.value

        // validação mínima
        val canSave = state.coletes.size == 5 && state.semColetes.size == 5 && state.phase == TeamPickFase.DONE
        if (!canSave) {
            _uiState.update { it.copy(error = "Escolhe 5 jogadores por equipa antes de guardar.") }
            return
        }

        _uiState.update { it.copy(isSaving = true, error = null) }

        viewModelScope.launch {
            try {
                val request = CreateMatchRequest(
                    coletesIds = state.coletes,
                    semColetesIds = state.semColetes,
                    goalsByPlayer = state.goalsByPlayer,
                    createdAt = System.currentTimeMillis()
                )

                val matchId = matchRepository.addMatch(request)

                _uiState.update {
                    it.copy(isSaving = false, savedMatchId = matchId)
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(isSaving = false, error = "Erro ao guardar jogo")
                }
            }
        }
    }
}
