package com.example.navexamplev2.data.repository

import com.example.navexamplev2.data.local.Cliente
import com.example.navexamplev2.data.local.ClienteDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val dao: ClienteDao) {

    val todosLosClientes: Flow<List<Cliente>> = dao.obtenerTodo()

    suspend fun insertar(cliente: Cliente) = dao.insertar(cliente)
    suspend fun actualizar(cliente: Cliente) = dao.actualizar(cliente)
    suspend fun eliminar(cliente: Cliente) = dao.eliminar(cliente)
}