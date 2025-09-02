package com.example.roomdb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia de la base de datos
        val db = Room.databaseBuilder(
            applicationContext,
            AgendaDatabase::class.java,
            "agenda.db"
        ).build()
        val dao = db.contactoDao()

        setContent {
            AgendaApp(dao)
        }
    }
}

@Composable
fun AgendaApp(dao: ContactoDao) {

    LaunchedEffect(Unit) { withContext(Dispatchers.IO) {
            // Insertar contactos de prueba
            dao.insert(Contacto(nombre = "Juan Perez", telefono = "7777777"))
            dao.insert(Contacto(nombre = "Maria Rodriguez", telefono = "8888888"))

            // Mostrar lista inicial
            var lista = dao.getAll().first()
            Log.d("AgendaDB", "Contactos iniciales: $lista")

            // Editar primer contacto
            if (lista.isNotEmpty()) {
                val contacto = lista[0]
                val editado = contacto.copy(nombre = "Juan Perez Editado", telefono = "9999999")
                dao.update(editado)

                lista = dao.getAll().first()
                Log.d("AgendaDB", "Contactos después de editar: $lista")
            }

            // Eliminar primer contacto
            lista = dao.getAll().first()
            if (lista.isNotEmpty()) {
                dao.delete(lista[0])
                lista = dao.getAll().first()
                Log.d("AgendaDB", "Contactos después de eliminar: $lista")
            }
        }
    }

}



