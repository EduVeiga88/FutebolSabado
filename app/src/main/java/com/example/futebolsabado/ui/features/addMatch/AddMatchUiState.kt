package com.example.futebolsabado.ui.features.addMatch

import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.domain.model.TeamPickFase

data class AddMatchUiState(
    val players: List<Player> = emptyList(),
    val phase: TeamPickFase = TeamPickFase.COLETES,
    val coletes: List<Int> = emptyList(),
    val semColetes: List<Int> = emptyList(),
    val goalsByPlayer: Map<Int, Int> = emptyMap(),
    val isSaving: Boolean = false,
    val error: String? = null,
    val savedMatchId: Long? = null // útil para navegação depois
)
