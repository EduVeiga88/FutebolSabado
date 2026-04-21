package com.example.futebolsabado.ui.features.matchList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.futebolsabado.domain.model.Match
import com.example.futebolsabado.ui.components.MatchItem
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun MatchListScreen(
    onAddMatchClick:() -> Unit,
    viewModel: MatchListViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddMatchClick) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar jogo")
            }
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when(state){
                MatchListUiState.Loading -> Text("Loading...")
                MatchListUiState.Empty -> Text("Sem Jogos")
                is MatchListUiState.Success -> {
                    MatchListContent(
                        matchs = state.matchs,
                        onDelete = viewModel::onDeleteMatch,

                    )
                }
                is MatchListUiState.Error -> {
                    Text("Erro: ${state.message}")
                }
            }
        }
    }
}


@Composable
fun MatchListContent(
    matchs: List<Match>,
    onDelete:(id:Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(matchs){match ->
            MatchItem(
                id = match.id,
                scoreColetes = match.scoreColetes,
                scoreSemColete = match.scoreSemColetes,
                createdAt = match.createdAt,
                onDelete = onDelete,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}


@Preview
@Composable
private fun MatchListPreview() {
    FutebolSabadoTheme {
        MatchListContent(
            listOf(
                Match(
                    id = 1,
                    coletes = emptyList(),
                    semColetes = emptyList(),
                    goals = emptyMap(),
                    scoreColetes = 3,
                    scoreSemColetes = 1,
                    createdAt = 1704547200000
                )
            ),
            onDelete = {}
        )
    }
    
}