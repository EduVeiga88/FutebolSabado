package com.example.futebolsabado.ui.components

import android.graphics.drawable.Icon
import androidx.annotation.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.futebolsabado.domain.model.Player
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun PlayerItem(
    modifier: Modifier = Modifier,
    player: Player,
    onDelete:(id:Int) -> Unit

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
            Text(player.nome, style = MaterialTheme.typography.labelMedium)

            Row(
                modifier = Modifier
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Jogos:${player.jogos}", style = MaterialTheme.typography.labelSmall,)
                Spacer(modifier = Modifier.width(5.dp))
                Text("Vitorias:${player.vitorias}", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.width(5.dp))
                Text("Golos:${player.golos}", style = MaterialTheme.typography.labelSmall,modifier= Modifier.weight(1f))

                IconButton(onClick = {onDelete(player.id)}) {
                    Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Apagar"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PlayerItemPreview() {
    FutebolSabadoTheme {
        PlayerItem(
            player = Player(
                id = 1,
                nome = "Pedro",
                jogos = 10,
                vitorias = 6,
                golos = 12
            ),
            onDelete = {}
        )
    }
}