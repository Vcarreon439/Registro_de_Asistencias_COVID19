package com.example.registrodeasistenciascovid_19.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carreras")
data class Carrera(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idCarrera") var idCarrera: String,
    @ColumnInfo(name = "nombreCarrera") var nombreCarrera: String
)