package com.example.navexamplev2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navexamplev2.navigation.Screen

@Composable
fun Pantalla2(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "PANTALLA2",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )


        Row() {
            Button(onClick = {
                navController.popBackStack()
            }
            ) {
                Text("Ir a PANTALLA1")
            }

            Spacer(modifier = Modifier.width(40.dp))

            Button(onClick = {
                navController.navigate(Screen.Pantalla3.route)
            }
            ) {
                Text("Ir a PANTALLA3")
            }
        }
    }
}