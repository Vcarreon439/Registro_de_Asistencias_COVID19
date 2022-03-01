package com.example.registrodeasistenciascovid_19.entities

import androidx.room.*

@Entity(tableName = "materias",
    foreignKeys = [ForeignKey(entity = Carrera::class,
        parentColumns = ["idCarrera"], childColumns = ["id_carrera"])])
data class Materia(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "CodMateria") var CodMateria: String,
    @ColumnInfo(name = "nombreMateria") var nombreMateria: String,

    @ColumnInfo(name = "semetre") var semestre: Int,
    @ColumnInfo(name = "id_carrera") var id_carrera: String
)
