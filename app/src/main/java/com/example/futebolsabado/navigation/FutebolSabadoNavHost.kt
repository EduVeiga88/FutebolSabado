package com.example.futebolsabado.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futebolsabado.ui.features.MenuScreen
import com.example.futebolsabado.ui.features.PlayersListScreen
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
            Text("AddMatchScreen(EmContrução)")
        }
    }
}