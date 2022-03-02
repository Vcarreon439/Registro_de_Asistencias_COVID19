package com.example.registrodeasistenciascovid_19.repositories

import androidx.lifecycle.LiveData
import com.example.registrodeasistenciascovid_19.Dao.MateriaDao
import com.example.registrodeasistenciascovid_19.entities.Materia

class MateriaRepository(private val materiaDao: MateriaDao) {

    val readAll: LiveData<List<Materia>> = materiaDao.leerTodas()

    suspend fun agregarMateria(materia: Materia){
        materiaDao.AgregarMateria(materia)
    }

    fun materiasSemestre(idCarrera: String, numSemestre: Int): LiveData<List<Materia>>{
        return materiaDao.materiasSemestre(idCarrera, numSemestre)
    }
}