package com.example.navexamplev2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.navexamplev2.data.local.Cliente
import com.example.navexamplev2.data.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClienteViewModel(private val repository: AppRepository) : ViewModel() {

    val clientes: StateFlow<List<Cliente>> = repository.todosLosClientes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    var clienteEncontrado by mutableStateOf<Cliente?>(null)
        private set

    var mensaje by mutableStateOf<String?>(null)
        private set

    fun agregarCliente(nombre: String, apellido: String, edad: Int) {
        viewModelScope.launch {
            repository.insertar(Cliente(nombre = nombre, apellido = apellido, edad = edad))
            mensaje = "Cliente agregado Correctamente!"
        }
    }

    fun buscarPorId(id: Int) {
        viewModelScope.launch {
            clienteEncontrado = repository.obtenerPorId(id)
            mensaje = if (clienteEncontrado == null) "No existe cliente con id" else null
        }
    }

    fun eliminarCliente(cliente: Cliente) {
        viewModelScope.launch {
            repository.eliminar(cliente)
            if (clienteEncontrado?.id == cliente.id) clienteEncontrado = null
            mensaje = "Cliente eliminado!"
        }
    }

    fun limpiarMensaje() {
        mensaje = null
    }
}

class ClienteViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClienteViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
    }
}