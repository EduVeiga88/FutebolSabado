package com.example.futebolsabado.ui.features.addMatch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.futebolsabado.domain.Match
import com.example.futebolsabado.domain.Player
import com.example.futebolsabado.domain.TeamPickFase
import com.example.futebolsabado.ui.components.PlayerAvatar
import com.example.futebolsabado.ui.components.PlayerGoalRow
import com.example.futebolsabado.ui.theme.FutebolSabadoTheme

@Composable
fun AddMatchScreen() {
    FutebolSabadoTheme {
        val players = listOf(
            Player(id = 1,  "Pedro",10,  6, 12),
            Player(id = 2, "Luis", 8, 3, 5),
            Player(id = 3, "Butra",8, 3, 5),
            Player(id = 4, "JP", 8, 3, 5),
            Player(id = 5, "Toze",8, 3, 5),
            Player(id = 6, "Edu", 8, 3, 5),
            Player(id = 7, "Almerindo",8, 3, 5),
            Player(id = 8, "Paulo",8, 3, 5),
            Player(id = 9, "Silva", 8, 3, 5),
            Player(id = 10, "João", 8, 3, 5)
        )


        val coletes = remember { mutableStateListOf<Int>() }
        val semColetes = remember { mutableStateListOf<Int>() }
        var phase = remember { TeamPickFase.COLETES }


        val goalsByPlayer = remember { mutableStateMapOf<Int, Int>() }

        fun isSelectedAnyTeam(id: Int): Boolean = id in coletes || id in semColetes

        fun togglePick(id: Int) {
            when (phase) {
                TeamPickFase.COLETES -> {

                    if (id in coletes) {
                        coletes.remove(id)
                        return
                    }


                    if (id in semColetes) return

                    // max 5
                    if (coletes.size >= 5) return

                    coletes.add(id)


                    if (coletes.size == 5) phase = TeamPickFase.SEMCOLETES
                }

                TeamPickFase.SEMCOLETES -> {
                    if (id in semColetes) {
                        semColetes.remove(id)
                        return
                    }

                    if (id in coletes) return
                    if (semColetes.size >= 5) return

                    semColetes.add(id)

                    if (semColetes.size == 5) phase = TeamPickFase.DONE
                }

                TeamPickFase.DONE -> {

                    return
                }
            }
        }

        fun incGoal(playerId: Int) {

            if (!isSelectedAnyTeam(playerId)) return
            val current = goalsByPlayer[playerId] ?: 0
            goalsByPlayer[playerId] = current + 1
        }

        fun decGoal(playerId: Int) {
            if (!isSelectedAnyTeam(playerId)) return
            val current = goalsByPlayer[playerId] ?: 0
            if (current <= 0) return
            goalsByPlayer[playerId] = current - 1
        }

        fun addMatch(match: Match){

        }

        AddMatchContent(
            players = players,
            coletes = coletes,
            semColetes = semColetes,
            phase = phase,
            goalsByPlayer = goalsByPlayer,
            onPlayerClick = ::togglePick,
            onIncrementGoal = ::incGoal,
            onDecrementGoal = ::decGoal,
            onAddMatchClick = addMatch
        )
    }
}

@Composable
fun AddMatchContent(
    players: List<Player>,
    coletes: List<Int>,
    semColetes: List<Int>,
    phase: TeamPickFase,
    goalsByPlayer: Map<Int, Int>,
    onPlayerClick: (Int) -> Unit,
    onIncrementGoal: (Int) -> Unit,
    onDecrementGoal: (Int) -> Unit,
    onAddMatchClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 400.dp, height = 200.dp)
            ) {
                val title = when (phase) {
                    TeamPickFase.COLETES -> "Coletes (${coletes.size}/5)"
                    TeamPickFase.SEMCOLETES -> "Sem Coletes (${semColetes.size}/5)"
                    TeamPickFase.DONE -> "Equipas completas"
                }

                Text(
                    title,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                val selectedIds = when (phase) {
                    TeamPickFase.COLETES -> coletes
                    TeamPickFase.SEMCOLETES -> semColetes
                    TeamPickFase.DONE -> emptyList()
                }

                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(players) { player ->
                        val isSelected = player.id in selectedIds

                        PlayerAvatar(
                            player = player,
                            isSelected = isSelected,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clickable { onPlayerClick(player.id) }
                        )
                    }
                }
            }


            val coletesPlayers = players.filter { it.id in coletes }
            val semPlayers = players.filter { it.id in semColetes }

            val coletesTotal = coletes.sumOf { id -> goalsByPlayer[id] ?: 0 }
            val semColetesTotal = semColetes.sumOf { id -> goalsByPlayer[id] ?: 0 }

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .height(350.dp)
            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Coletes",
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                        )

                        coletesPlayers.forEach { player ->
                            val goals = goalsByPlayer[player.id] ?: 0

                            PlayerGoalRow(
                                player = player,
                                goals = goals,
                                onInc = { onIncrementGoal(player.id) },
                                onDec = { onDecrementGoal(player.id) }
                            )
                        }
                    }

                    VerticalDivider(
                        modifier = Modifier.padding(vertical = 2.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Sem Coletes",
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                        )

                        semPlayers.forEach { player ->
                            val goals = goalsByPlayer[player.id] ?: 0

                            PlayerGoalRow(
                                player = player,
                                goals = goals,
                                onInc = { onIncrementGoal(player.id) },
                                onDec = { onDecrementGoal(player.id) }
                            )

                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .size(width = 400.dp, height = 100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Resultado",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "$coletesTotal - $semColetesTotal",
                        style = MaterialTheme.typography.headlineMedium

                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                enabled = phase == TeamPickFase.DONE,
                onClick = { onAddMatchClick() }
            ) {
                Text(
                    "Adicionar Jogo",
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }
    }
}

@Preview
@Composable
private fun AddMatchPreview() {
    AddMatchScreen()
}
