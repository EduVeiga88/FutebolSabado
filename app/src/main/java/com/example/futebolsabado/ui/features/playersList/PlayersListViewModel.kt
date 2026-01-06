package com.example.futebolsabado.ui.features.playersList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futebolsabado.data.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayersListViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {

    var uiState by mutableStateOf<PlayersListUiState>(PlayersListUiState.Loading)
        private set

    init {
        observePlayers()
    }

    private fun observePlayers() {
        viewModelScope.launch {
            repository.getAll()
                .catch { e ->
                    uiState = PlayersListUiState.Error(e.message ?: "Unknown error")
                }
                .collectLatest { players ->
                    uiState =
                        if (players.isEmpty()) PlayersListUiState.Empty
                        else PlayersListUiState.Success(players)
                }
        }
    }
}
