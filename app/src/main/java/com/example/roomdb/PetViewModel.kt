package com.example.roomdb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PetViewModel(private val repository: PetRepository) : ViewModel() {

    val pets = repository.pets.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun addPet(name: String, age: Int, colour: String) {
        viewModelScope.launch {
            repository.insert(Pet(name = name, age = age, colour = colour))
        }
    }

    fun updatePet(pet: Pet) {
        viewModelScope.launch {
            repository.update(pet)
        }
    }

    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            repository.delete(pet)
        }
    }
}
