package com.example.registrodeasistenciascovid_19.entities

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "semestre",
    foreignKeys = [ForeignKey(entity = Carrera::class,
        parentColumns = ["idCarrera"], childColumns = ["id_Carrera"])])
data class Semestre(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "entry") var entry: Int,

    @ColumnInfo(name = "cantSemestres") var cantSemestres: Int,
    @ColumnInfo(name = "id_Carrera") var id_Carrera: String
)