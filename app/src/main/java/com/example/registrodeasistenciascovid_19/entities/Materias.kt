package com.example.registrodeasistenciascovid_19.entities

import androidx.room.*

@Entity(tableName = "materias")
data class Materia(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "CodMateria") var CodMateria: String,
    @ColumnInfo(name = "nombreMateria") var nombreMateria: String,
    @ColumnInfo(name = "semetre") var semestre: Int,
)
