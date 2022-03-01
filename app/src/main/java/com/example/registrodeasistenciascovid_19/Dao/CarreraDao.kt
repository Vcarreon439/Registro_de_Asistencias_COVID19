package com.example.registrodeasistenciascovid_19.Dao

import android.app.Notification
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registrodeasistenciascovid_19.entities.Carrera

@Dao
interface CarreraDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun AgregarCarrera(carrera: Carrera)

    @Query("Select * from carreras")
    fun leerTodas(): LiveData<List<Carrera>>

    @Query("Delete from carreras")
    suspend fun Wipe()
}