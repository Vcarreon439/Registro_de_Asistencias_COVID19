package com.example.registrodeasistenciascovid_19.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registrodeasistenciascovid_19.entities.Materia

@Dao
interface MateriaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AgregarMateria(materia: Materia)

    @Query("Select * from materias")
    fun leerTodas(): LiveData<List<Materia>>
}