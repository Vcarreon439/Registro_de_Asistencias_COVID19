package com.example.registrodeasistenciascovid_19.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clases")
data class Clases(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Cod_Clase") var Cod_Clase: String,
    @ColumnInfo(name = "codDocente") var codDocente: String,
    @ColumnInfo(name = "codAula") var codAula: String,
    @ColumnInfo(name = "Inicio") var inicio: String,
    @ColumnInfo(name = "Duracion") var duracion: Int
)
