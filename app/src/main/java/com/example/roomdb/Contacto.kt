package com.example.roomdb


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactos")
data class Contacto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val telefono: String)