package com.example.registrodeasistenciascovid_19.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.registrodeasistenciascovid_19.entities.Clases

@Dao
interface ClasesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun AgregarClase(clases: Clases)

    @Query(value = "select * from clases")
    fun obtenerClasesInternas(): LiveData<List<Clases>>

    @Delete
    suspend fun delete(clases: Clases)

}