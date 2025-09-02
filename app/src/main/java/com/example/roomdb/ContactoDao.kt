package com.example.roomdb


import androidx.room.*
import androidx.room.Query
import kotlinx.coroutines.flow.*

@Dao
interface ContactoDao {
    @Query("SELECT * FROM contactos")
    fun getAll(): Flow<List<Contacto>>

    @Insert
    suspend fun insert(contacto: Contacto)

    @Update
    suspend fun update(contacto: Contacto)

    @Delete
    suspend fun delete(contacto: Contacto)
}