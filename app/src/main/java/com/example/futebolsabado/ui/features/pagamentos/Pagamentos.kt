package com.example.futebolsabado.ui.features.pagamentos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.ui.components.PagamentosItem
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PagamentosScreen(
    viewModel: PagamentosViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    when {
        state.isLoading -> Text("Loading...")
        state.errorMessage != null -> Text("Erro: ${state.errorMessage}")
        else -> {
            PagamentosContent(
                players = state.players,
                paidMonthsByPlayer = state.paidMonthsByPlayer,
                onSave = viewModel::onSave,
                onToggleMonth = viewModel::onToggleMonth
            )
        }
    }
}

@Composable
fun PagamentosContent(
    players: List<Player>,
    paidMonthsByPlayer: Map<Int, Set<Int>>,
    onSave: () -> Unit,
    onToggleMonth:(playerId: Int, month: Int) -> Unit
) {
    Scaffold(
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = true,
                onClick = onSave
            ) {
                Text(
                    "Guardar",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
                items(players){player ->
                    val paidMonths = paidMonthsByPlayer[player.id].orEmpty()

                    PagamentosItem(
                        player = player,
                        paidMonth = paidMonths,
                        onToggleMonth = { month ->
                            onToggleMonth(
                                player.id, month
                            )
                        }
                    )
                }
        }
    }
}


@Preview
@Composable
private fun PagamentosPrev() {
    FutebolSabadoTheme {
        PagamentosContent(
            players = listOf(
                Player(1, "Edu", 0, 0, 0),
                Player(2, "João", 0, 0, 0)
            ),
            paidMonthsByPlayer = mapOf(

            ),
            onSave = {},
            onToggleMonth = { _, _ -> }
        )
    }
}

