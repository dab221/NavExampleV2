package com.example.navexamplev2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navexamplev2.navigation.Screen
import com.example.navexamplev2.viewmodel.ClienteViewModel

@Composable
fun Pantalla2(navController: NavController, viewModel: ClienteViewModel) {

    val clientes by viewModel.clientes.collectAsState()
    var idBuscado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "PANTALLA2", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = idBuscado,
                onValueChange = { idBuscado = it },
                label = { Text("Buscar por ID") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                idBuscado.toIntOrNull()?.let { viewModel.buscarPorId(it) }
            }) {
                Text("Buscar")
            }
        }

        viewModel.clienteEncontrado?.let { c ->
            Text("Encontrado: ${c.nombre} ${c.apellido}, ${c.edad} años")
        }
        viewModel.mensaje?.let { Text(it) }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("Todos los clientes:", fontWeight = FontWeight.Bold)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(clientes, key = { it.id }) { cliente ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("${cliente.nombre} ${cliente.apellido}")
                        Text("Edad: ${cliente.edad}")
                    }
                    IconButton(onClick = { viewModel.eliminarCliente(cliente) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
                HorizontalDivider()
            }
        }

        Row {
            Button(onClick = { navController.popBackStack() }) {
                Text("Ir a PANTALLA1")
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(onClick = { navController.navigate(Screen.Pantalla3.route) }) {
                Text("Ir a PANTALLA3")
            }
        }
    }
}