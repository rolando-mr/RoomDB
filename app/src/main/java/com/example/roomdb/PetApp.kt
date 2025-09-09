package com.example.roomdb

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PetApp(viewModel: PetViewModel) {
    val pets by viewModel.pets.collectAsState()

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var colour by remember { mutableStateOf("") }
    var editingPet by remember { mutableStateOf<Pet?>(null) }

    Column(Modifier.padding(16.dp)) {
        Text("CRUD Mascotas (Room)", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(pets) { pet ->
                Card(
                    Modifier.fillMaxWidth().padding(4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text("Nombre: ${pet.name}")
                        Text("Edad: ${pet.age}")
                        Text("Color: ${pet.colour}")
                        Row {
                            Button(onClick = {
                                editingPet = pet
                                name = pet.name
                                age = pet.age.toString()
                                colour = pet.colour
                            }, modifier = Modifier.padding(end = 8.dp)) {
                                Text("Editar")
                            }
                            Button(onClick = { viewModel.deletePet(pet) }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
        OutlinedTextField(value = colour, onValueChange = { colour = it }, label = { Text("Color") }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && age.isNotBlank() && colour.isNotBlank()) {
                    if (editingPet == null) {
                        viewModel.addPet(name, age.toInt(), colour)
                    } else {
                        viewModel.updatePet(editingPet!!.copy(name = name, age = age.toInt(), colour = colour))
                    }
                    name = ""
                    age = ""
                    colour = ""
                    editingPet = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingPet == null) "Agregar Mascota" else "Actualizar Mascota")
        }
    }
}
