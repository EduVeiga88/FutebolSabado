package com.example.futebolsabado.ui.features.addPlayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


data class AddPlayerUiState(
    val name: String = "",
    val jogosText: String = "",
    val vitoriasText: String = "",
    val golosText: String = ""
){
    val canSave: Boolean get() = name.isNotBlank()
}


@HiltViewModel
class AddPlayerViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(AddPlayerUiState())
        private set

    fun onNameChange(value: String){
        uiState = uiState.copy(name = value)
    }

    fun onJogosChange(value: String){
        uiState = uiState.copy(jogosText = value)
    }

    fun onVitoriasChange(value: String){
        uiState = uiState.copy(vitoriasText = value)
    }

    fun onGolosChange(value: String){
        uiState = uiState.copy(golosText = value)
    }

    fun savePlayer(onSuccess:() -> Unit){
        val jogos = uiState.jogosText.toIntOrNull() ?: 0
        val vitorias = uiState.vitoriasText.toIntOrNull() ?: 0
        val golos = uiState.golosText.toIntOrNull() ?: 0

        if (uiState.name.isBlank()) return

        onSuccess()
    }

}