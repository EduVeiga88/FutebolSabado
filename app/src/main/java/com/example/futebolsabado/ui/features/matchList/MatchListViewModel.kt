package com.example.futebolsabado.ui.features.matchList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futebolsabado.domain.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    var uiState by mutableStateOf<MatchListUiState>(MatchListUiState.Loading)
        private set

    init {
        observeMatchs()
    }

    private fun observeMatchs() {
        viewModelScope.launch {
            repository.getAllMatchs()
                .catch { e ->
                    uiState = MatchListUiState.Error(e.message ?: "Erro ao carregar jogos")
                }
                .collect { matchs ->
                    uiState = if (matchs.isEmpty()) {
                        MatchListUiState.Empty
                    } else {
                        MatchListUiState.Success(matchs)
                    }
                }
        }
    }

    fun onDeleteMatch(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }
}
