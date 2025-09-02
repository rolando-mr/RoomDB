package com.example.roomdb

import androidx.room.Database
import androidx.room.*
import androidx.room.RoomDatabase

@Database(entities = [Contacto::class], version = 1, exportSchema = false)
abstract class AgendaDatabase : RoomDatabase() {
    abstract fun contactoDao(): ContactoDao
}