package com.example.futebolsabado.ui.features.addMatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.Player
import com.example.futebolsabado.ui.components.PlayerAvatar
import com.example.futebolsabado.ui.components.PlayerGoalRow
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun AddMatchScreen(modifier: Modifier = Modifier) {
    FutebolSabadoTheme {
        AddMatchContent(
            players = listOf(
                Player(id = 1,nome = "Pedro",jogos = 10,vitorias = 6, golos = 12),
                Player(id = 2,"João", 8, 3, 5),
                Player(id = 3,"João", 8, 3, 5),
                Player(id = 4,"João", 8, 3, 5),
                Player(id = 5,"João", 8, 3, 5),
                Player(id = 6,"João", 8, 3, 5),
                Player(id = 7,"João", 8, 3, 5)
            )
        )
    }
}

@Composable
fun AddMatchContent(
    players: List<Player>
) {
    Scaffold {
        PaddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 400.dp, height = 200.dp)
                    .background(Color.Gray)

            ) {
                Text(
                    "Coletes",
                    modifier = Modifier
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(players) { player ->
                        PlayerAvatar(
                            player = player,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                    }

                }
            }
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Coletes",
                            modifier = Modifier
                                .padding(start = 16.dp, top = 16.dp)
                        )
                        PlayerGoalRow(
                            player = Player(id = 1, nome = "Pedro", jogos = 10, vitorias = 6, golos = 12),
                            goals = 0,
                            onInc = {},
                            onDec = {}
                        ) {

                        }
                    }

                    VerticalDivider(
                        modifier = Modifier.padding(vertical = 2.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Sem Coletes",
                            modifier = Modifier
                                .padding(start = 16.dp, top = 16.dp)
                        )
                    }

                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 2.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddMatchPreview() {
    FutebolSabadoTheme {
        AddMatchContent(
            players = listOf(
                Player(id = 1,nome = "Pedro",jogos = 10,vitorias = 6, golos = 12),
                Player(id = 2,"João", 8, 3, 5),
                Player(id = 3,"João", 8, 3, 5),
                Player(id = 4,"João", 8, 3, 5),
                Player(id = 5,"João", 8, 3, 5),
                Player(id = 6,"João", 8, 3, 5),
                Player(id = 7,"João", 8, 3, 5)
            )
        )
    }
    
}