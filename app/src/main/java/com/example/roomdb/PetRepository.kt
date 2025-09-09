package com.example.roomdb

class PetRepository(private val petDao: PetDao) {
    val pets = petDao.getAllPets()

    suspend fun insert(pet: Pet) = petDao.insertPet(pet)
    suspend fun update(pet: Pet) = petDao.updatePet(pet)
    suspend fun delete(pet: Pet) = petDao.deletePet(pet)
}
