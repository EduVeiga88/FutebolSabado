package com.example.futebolsabado.ui.features.addMatch

sealed interface AddMatchEvent {
    data class PlayerClicked(val playerId: Int) : AddMatchEvent
    data class IncrementGoal(val playerId: Int) : AddMatchEvent
    data class DecrementGoal(val playerId: Int) : AddMatchEvent
    data object SaveMatch : AddMatchEvent
    data object ClearError : AddMatchEvent
    data object ClearSaved : AddMatchEvent
}
