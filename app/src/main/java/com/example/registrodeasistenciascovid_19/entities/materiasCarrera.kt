package com.example.registrodeasistenciascovid_19.entities

import androidx.room.*
import com.example.registrodeasistenciascovid_19.entities.Materia

@Entity(
    tableName = "materiasCarrera",
    foreignKeys = [
        ForeignKey(
            entity = Carrera::class, parentColumns = ["idCarrera"],
            childColumns = ["codCarrera"]
        ),
        ForeignKey(
            entity = Materia::class, parentColumns = ["CodMateria"],
            childColumns = ["codMateria"]
        )
    ]
)
data class materiasCarrera(
    @ColumnInfo(name = "codCarrera")
    var codCarrera: String,
    @PrimaryKey
    @ColumnInfo(name = "codMateria")
    var codMateria: String
)

