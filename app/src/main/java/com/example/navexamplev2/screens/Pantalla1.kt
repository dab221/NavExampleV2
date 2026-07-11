package com.example.navexamplev2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navexamplev2.navigation.Screen
import com.example.navexamplev2.viewmodel.ClienteViewModel

@Composable
fun Pantalla1(navController: NavController, viewModel: ClienteViewModel) {

    var nombre by remember { mutableStateOf("")}
    var apellido by remember { mutableStateOf("")}
    var edad by remember { mutableStateOf("")}
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AGRAGAR CLIENTE",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it},
            label = { Text("Nombre")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it},
            label = { Text("Apellido")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it},
            label = { Text("Edad")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        error?.let { Text(text = it) }
        viewModel.mensaje?.let { Text(text = it)}

        Button(
            onClick = {
                val edadInt = edad.toIntOrNull()
                when {
                    nombre.isBlank() || apellido.isBlank() -> error = "Nombre y apellido son obligatorios"
                    edadInt == null -> error = "La edad debe ser un Número"
                    else -> {
                        error = null
                        viewModel.agregarCliente(nombre, apellido, edadInt)
                        nombre = ""
                        apellido = ""
                        edad = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Button(
            onClick = {
                viewModel.limpiarMensaje()
                navController.navigate(Screen.Pantalla2.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Pantalla2")
        }
    }
}