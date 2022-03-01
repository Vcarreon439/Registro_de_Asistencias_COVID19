package com.example.registrodeasistenciascovid_19.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registrodeasistenciascovid_19.entities.Semestre

@Dao
interface SemestreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AgregarSemestre(semestre: Semestre)

    @Query("Select * from semestre")
    fun leerTodas(): LiveData<List<Semestre>>
}