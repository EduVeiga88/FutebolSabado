package com.example.futebolsabado.ui.features.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Futebol de Sábado",
                fontSize = 30.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(250.dp)
                    .clickable { onPlayersClick() }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Jogadores",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(250.dp)
                    .clickable { onAddMatchClick() }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Add Jogo")
                }
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



