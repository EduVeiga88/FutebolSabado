package com.example.futebolsabado.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PlayerGoalRow(
    player: Player,
    goals: Int,
    onInc: () -> Unit,
    onDec: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(player.nome)
        GoalCounter(
            count = goals,
            onIncrement = onInc,
            onDecrement = onDec
        )
    }
}

@Preview
@Composable
private fun PlayerGoalRowPreview() {
    FutebolSabadoTheme {
        PlayerGoalRow(
            player = Player(id = 1, nome = "Pedro", jogos = 10, vitorias = 6, golos = 12),
            goals = 2,
            onInc = {},
            onDec = {}
        )
    }

}