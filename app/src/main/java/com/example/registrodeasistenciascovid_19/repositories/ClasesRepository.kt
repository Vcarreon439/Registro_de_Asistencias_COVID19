package com.example.registrodeasistenciascovid_19.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.registrodeasistenciascovid_19.Dao.ClasesDao
import com.example.registrodeasistenciascovid_19.entities.Clases

class ClasesRepository(private val clasesDao: ClasesDao) {

    val clasesInternas: LiveData<List<Clases>> = clasesDao.obtenerClasesInternas()

    suspend fun agregarClase(clase: Clases){
        clasesDao.AgregarClase(clase)
    }
     @WorkerThread
     suspend fun delete(clase: Clases){
         clasesDao.delete(clase)
     }

}