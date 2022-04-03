package com.example.registrodeasistenciascovid_19.repositories

import androidx.lifecycle.LiveData
import com.example.registrodeasistenciascovid_19.Dao.ClasesDao
import com.example.registrodeasistenciascovid_19.entities.Clases

class ClasesRepository(private val clasesDao: ClasesDao) {

    //val readAll: LiveData<List<Carrera>> = carreraDao.leerTodas()

    suspend fun agregarClase(clase: Clases){
        clasesDao.AgregarClase(clase)
    }

}