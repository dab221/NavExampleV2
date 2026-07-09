package com.example.navexamplev2.navigation

sealed class Screen(val route: String) {
    object Pantalla1: Screen("pantalla1")
    object Pantalla2: Screen("pantalla2")
    object Pantalla3: Screen("pantalla3")
}