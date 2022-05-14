package com.example.registrodeasistenciascovid_19.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.entities.Materia

@Dao
interface MateriaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AgregarMateria(materia: Materia)

    @Query("Select * from materias")
    fun leerTodas(): LiveData<List<Materia>>

    @Query("Select * from materiasCarrera A Inner Join materias B where A.codCarrera = :idCarrera and B.semetre = :numSemestre")
    fun materiasSemestre(idCarrera: String, numSemestre: Int): LiveData<List<Materia>>

}