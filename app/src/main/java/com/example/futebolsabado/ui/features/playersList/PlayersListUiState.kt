package com.example.futebolsabado.ui.features.playersList

import com.example.futebolsabado.domain.model.Player

sealed class PlayersListUiState {

    object Loading : PlayersListUiState()
    object Empty : PlayersListUiState()
    data class Success(val players: List<Player>) : PlayersListUiState()
    data class Error(val message: String) : PlayersListUiState()

}