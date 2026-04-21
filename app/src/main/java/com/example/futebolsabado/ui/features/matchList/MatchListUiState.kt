package com.example.futebolsabado.ui.features.matchList

import com.example.futebolsabado.domain.model.Match

sealed class MatchListUiState{

    data object Loading : MatchListUiState()

    data object Empty : MatchListUiState()

    data class Success(val matchs: List<Match>) : MatchListUiState()

    data class Error(val message: String) : MatchListUiState()


}


