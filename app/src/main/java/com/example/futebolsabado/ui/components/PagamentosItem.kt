package com.example.futebolsabado.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PagamentosItem(
    paidMonth:Set<Int>,
    onToggleMonth:(month: Int) -> Unit,
    player: Player
) {
    Surface(
        modifier = Modifier
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
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(player.nome,style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MonthChipItem("Jan",paidMonth.contains(1)) {onToggleMonth(1)}
                MonthChipItem("Fev",paidMonth.contains(2)) {onToggleMonth(2)}
                MonthChipItem("Mar",paidMonth.contains(3)) {onToggleMonth(3)}
                MonthChipItem("Abr",paidMonth.contains(4)) {onToggleMonth(4)}

            }
            Row(
                modifier = Modifier
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MonthChipItem("Mai",paidMonth.contains(5)) {onToggleMonth(5)}
                MonthChipItem("Jun",paidMonth.contains(6)) {onToggleMonth(6)}
                MonthChipItem("Jul",paidMonth.contains(7)) {onToggleMonth(7)}
                MonthChipItem("Ago",paidMonth.contains(8)) {onToggleMonth(8)}

            }
            Row(
                modifier = Modifier
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MonthChipItem("Set",paidMonth.contains(9)) {onToggleMonth(9) }
                MonthChipItem("Out",paidMonth.contains(10)) {onToggleMonth(10)}
                MonthChipItem("Nov",paidMonth.contains(11)) {onToggleMonth(11)}
                MonthChipItem("Dez",paidMonth.contains(12)) {onToggleMonth(12)}

            }
        }
    }
}

@Preview
@Composable
private fun PagamentosItemPreview() {
    FutebolSabadoTheme {
        PagamentosItem(
            player = Player(1, "Eduardo", 0, 0, 0),
            paidMonth = emptySet(),
            onToggleMonth = {}
        )
    }
}