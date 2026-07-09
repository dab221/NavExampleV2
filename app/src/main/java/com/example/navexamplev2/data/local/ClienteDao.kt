package com.example.navexamplev2.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Insert
    suspend fun insertar(cliente: Cliente)

    @Update
    suspend fun actualizar(cliente: Cliente)

    @Delete
    suspend fun eliminar(cliente: Cliente)

    @Query("SELECT * FROM clientes ORDER BY apellido ASC")
    suspend fun obtenerTodo(): Flow<List<Cliente>>

    @Query("SELECT * FROM clientes WHERE id = :clienteId")
    suspend fun obtenerPorId(clienteId: Int): Cliente?

    @Query("SELECT * FROM clientes WHERE edad >= :edadMinima")
    suspend fun obtenerPorEdadMinima(edadMinima: Int): Flow<List<Cliente>>
}