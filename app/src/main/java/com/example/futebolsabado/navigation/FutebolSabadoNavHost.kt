package com.example.futebolsabado.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futebolsabado.ui.features.addMatch.AddMatchScreen
import com.example.futebolsabado.ui.features.menu.MenuScreen
import com.example.futebolsabado.ui.features.playersList.PlayersListScreen
import kotlinx.serialization.Serializable

@Serializable
object MenuRoute

@Serializable
object PlayersRoute

@Serializable
object AddMatchRoute



@Composable
fun FutebolSabadoNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = MenuRoute ) {
        composable<MenuRoute>{
            MenuScreen(
                        onPlayersClick = { navController.navigate(PlayersRoute)},
                        onAddMatchClick = { navController.navigate(AddMatchRoute)
                }
            )
        }
        composable<PlayersRoute>{

            PlayersListScreen()

        }
        composable<AddMatchRoute>{

            AddMatchScreen()
        }
    }
}