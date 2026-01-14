package com.example.futebolsabado.ui.features.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.futebolsabado.ui.components.MenuCard
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme


@Composable
fun MenuScreen(
    onPlayersClick: () -> Unit,
    onAddMatchClick: () -> Unit
) {
    MenuContent(
        onPlayersClick = onPlayersClick,
        onAddMatchClick = onAddMatchClick
    )
}

@Composable
fun MenuContent(
    onPlayersClick: () -> Unit,
    onAddMatchClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(top = 75.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Futebol de Sábado",
                fontSize = 30.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            Row {
                MenuCard(
                    title = "Jogadores",
                    icon = Icons.Default.Person,
                    onClick = onPlayersClick

                )
                MenuCard(
                    title = "Jogos",
                    icon = Icons.Default.SportsSoccer,
                    onClick = onAddMatchClick
                )
            }
                Row {
                    MenuCard(
                        title = "Estatisticas",
                        icon = Icons.Default.BarChart,
                        onClick = {}
                    )
                    MenuCard(
                        title = "Pagamentos",
                        icon = Icons.Default.Payments,
                        onClick = {}
                    )
                }
            }
        }
    }



@Preview
@Composable
private fun MenuContentPreview(){
    FutebolSabadoTheme {
        MenuContent(
            onPlayersClick = {},
            onAddMatchClick = {}
        )
    }
}



