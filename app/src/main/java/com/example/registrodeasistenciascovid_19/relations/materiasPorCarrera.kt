package com.example.registrodeasistenciascovid_19.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.entities.Materia
import com.example.registrodeasistenciascovid_19.entities.materiasCarrera

data class materiasPorCarrera (
    @Embedded val carrera: Carrera,
    @Relation(
        parentColumn = "idCarrera",
        entityColumn = "codCarrera",
        associateBy = Junction(materiasCarrera::class)
    )
    val materiasC:List<Materia>
)
