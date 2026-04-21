package com.example.futebolsabado.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme
import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("SimpleDateFormat")
@Composable
fun MatchItem(
    id: Long,
    scoreColetes: Int,
    scoreSemColete: Int,
    createdAt: Long,
    onDelete:(id:Long) -> Unit,
    modifier: Modifier
) {
    val dateFormatter = remember {
        SimpleDateFormat("dd/MM/yyyy")
    }

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
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Jogo dia: ${dateFormatter.format(createdAt)}",style = MaterialTheme.typography.labelMedium)

            Row(
                modifier = Modifier
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Resultado: $scoreColetes - $scoreSemColete",style = MaterialTheme.typography.labelSmall,modifier= Modifier.weight(1f))
                IconButton(onClick = {onDelete(id)}) {
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
private fun MatchItemPreview() {
    FutebolSabadoTheme {
        MatchItem(
            id = 1,
            scoreColetes = 2,
            scoreSemColete = 2,
            createdAt = 21012026,
            onDelete = {},
            modifier = Modifier
        )
    }
}