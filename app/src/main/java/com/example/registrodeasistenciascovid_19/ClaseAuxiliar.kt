package com.example.registrodeasistenciascovid_19

class Carrera constructor(var nombre: String, var clave: String, var Semestres: MutableList<Semestre>? = null){
}

class Semestre constructor(var numSemestre: Int, var listaMaterias: MutableList<Materia>){

}

class Materia constructor(var nombre: String, var codigo: String){

}

class Arbol{

    var listaCarreras: MutableList<Carrera> = mutableListOf()

    fun AgregarCarrera()
    {
        var SistemasComp = Carrera("Sistemas Computacionales","IIIS")

        for (i in 1..9){
            var Materias = mutableListOf<Materia>()
            var SemestreA = Semestre(i, Materias)
            //SistemasComp.listaSemestre.add(SemestreA)
        }

        this.listaCarreras.add(SistemasComp)

    }
}