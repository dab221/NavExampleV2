package com.example.navexamplev2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navexamplev2.screens.Pantalla1
import com.example.navexamplev2.screens.Pantalla2
import com.example.navexamplev2.screens.Pantalla3

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Pantalla1.route
        ) {
        composable(Screen.Pantalla1.route) {
            Pantalla1(navController)
        }
        composable(Screen.Pantalla2.route) {
            Pantalla2(navController)
        }
        composable(Screen.Pantalla3.route) {
            Pantalla3(navController)
        }
    }
}