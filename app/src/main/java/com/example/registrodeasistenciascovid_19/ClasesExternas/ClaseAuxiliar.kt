package com.example.registrodeasistenciascovid_19

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

class Carrera constructor(var nombre: String, var clave: String, var Semestres: MutableList<Semestre>? = null){ }

class Semestre constructor(var numSemestre: Int, var listaMaterias: MutableList<Materia>){ }

class Materia constructor(var nombre: String, var codigo: String){ }


