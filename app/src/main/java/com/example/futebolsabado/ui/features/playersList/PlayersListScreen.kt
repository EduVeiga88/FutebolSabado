package com.example.futebolsabado.ui.features.playersList


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.Player
import com.example.futebolsabado.ui.components.PlayerItem
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PlayersListScreen(viewModel: PlayersListViewModel = androidx.hilt.navigation.compose.hiltViewModel()) {
    when (val state = viewModel.uiState) {
        PlayersListUiState.Loading -> Text("Loading...")
        PlayersListUiState.Empty -> Text("Sem jogadores")
        is PlayersListUiState.Success -> PlayersListContent(players = state.players)
        is PlayersListUiState.Error -> Text("Erro: ${state.message}")
    }
}



@Composable
fun PlayersListContent(
    players: List<Player>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(players){player ->
            PlayerItem(
                player = player,
                modifier = Modifier.padding(bottom = 8.dp)

            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun PlayersListPreview() {
        FutebolSabadoTheme {
            PlayersListContent(
                players = listOf(
                    Player(id = 1,nome = "Pedro",jogos = 10,vitorias = 6, golos = 12),
                    Player(id = 2,"João", 8, 3, 5)
                )
            )
    }
}