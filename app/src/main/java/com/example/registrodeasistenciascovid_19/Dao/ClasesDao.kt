package com.example.registrodeasistenciascovid_19.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.registrodeasistenciascovid_19.entities.Clases

@Dao
interface ClasesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun AgregarClase(clases: Clases)
}