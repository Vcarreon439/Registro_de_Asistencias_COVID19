package com.example.registrodeasistenciascovid_19.repositories

import androidx.lifecycle.LiveData
import com.example.registrodeasistenciascovid_19.Dao.CarreraDao
import com.example.registrodeasistenciascovid_19.entities.Carrera

class CarreraRepository(private val carreraDao: CarreraDao) {
    val readAll: LiveData<List<Carrera>> = carreraDao.leerTodas()

    suspend fun agregarCarrera(carrera: Carrera){
        carreraDao.AgregarCarrera(carrera)
    }

}