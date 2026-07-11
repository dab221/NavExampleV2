package com.example.navexamplev2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navexamplev2.data.local.AppDatabase
import com.example.navexamplev2.data.repository.AppRepository
import com.example.navexamplev2.screens.Pantalla1
import com.example.navexamplev2.screens.Pantalla2
import com.example.navexamplev2.screens.Pantalla3
import com.example.navexamplev2.viewmodel.ClienteViewModel
import com.example.navexamplev2.viewmodel.ClienteViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val repository = remember {
        AppRepository(AppDatabase.getDatabase(context).clienteDao())
    }
    val clienteViewModel: ClienteViewModel = viewModel(
        factory = ClienteViewModelFactory(repository)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Pantalla1.route
        ) {
        composable(Screen.Pantalla1.route) {
            Pantalla1(navController, clienteViewModel)
        }
        composable(Screen.Pantalla2.route) {
            Pantalla2(navController, clienteViewModel)
        }
        composable(Screen.Pantalla3.route) {
            Pantalla3(navController)
        }
    }
}