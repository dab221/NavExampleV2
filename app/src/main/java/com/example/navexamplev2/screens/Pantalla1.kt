package com.example.navexamplev2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navexamplev2.navigation.Screen

@Composable
fun Pantalla1(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "PANTALLA1",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )


        Button(onClick = {
            navController.navigate(Screen.Pantalla2.route)
        }
        ) {
            Text("Ir a PANTALLA2")
        }
    }
}