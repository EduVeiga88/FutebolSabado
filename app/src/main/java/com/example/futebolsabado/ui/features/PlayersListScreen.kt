package com.example.futebolsabado.ui.features

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.Jogador
import com.example.futebolsabado.ui.components.PlayerItem
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PlayersListScreen() {
    
}


@Composable
fun PlayersListContent(
    jogadores: List<Jogador>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(jogadores){jogador ->
            PlayerItem(
                jogador = jogador,
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
                jogadores = listOf(
                    Jogador("Pedro", 10, 6, 12),
                    Jogador("João", 8, 3, 5)
                )
            )
    }
}