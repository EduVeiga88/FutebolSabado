package com.example.futebolsabado.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.Jogador
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PlayerItem(
    modifier: Modifier = Modifier,
    jogador: Jogador

) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )

    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(jogador.nome, style = MaterialTheme.typography.labelMedium)

            Row(
                modifier = Modifier
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Jogos:${jogador.jogos}", style = MaterialTheme.typography.labelSmall,)
                Spacer(modifier = Modifier.width(5.dp))
                Text("Vitorias:${jogador.vitorias}", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.width(5.dp))
                Text("Golos:${jogador.golos}", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Preview
@Composable
private fun PlayerItemPreview() {
    FutebolSabadoTheme {
        PlayerItem(
            jogador = Jogador(
                nome = "Pedro",
                jogos = 10,
                vitorias = 6,
                golos = 12
            )
        )
    }
}