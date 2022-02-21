package com.example.registrodeasistenciascovid_19

class Carreras {

    var lista: MutableList<Carrera> = mutableListOf (
        Carrera("Sistemas Automotrices", "ISAU-2013-240"),
        Carrera("Gestión Empresarial", "IGEM-2009-201"),
        Carrera("Ambiental", "IAMB-2010-206"),
        Carrera("Informática", "INF-2010-220"),
        Carrera("Electromecánica", "IEME 2010-210"),
        Carrera("Electrónica", "IELC-2010-211"),
        Carrera("Industrial", "IND-2010-227"),
        Carrera("Industrial Nocturno", "IND-2010-227"),
        Carrera("Sistemas Computacionales", "ISIC-2010-224"),
        Carrera("Especializacion Mecatrónica", "ESMEC"),
        Carrera("Maestria Mecatrónica", "MAMECA")
    )

    init {
        lista
    }
}

class SemestresAutomotrices{

    class PrimerSemestre{
        companion object MateriasPrimerSemestre{
            var Materias =  Semestre(1, mutableListOf(
                Materia("Cálculo Diferencial","ACF-0901"),
                Materia("Taller de Ética","ACA-0907"),
                Materia("Fundamentos de Investigación","ACC-0906"),
                Materia("Programación Básica","SAC-1330"),
                Materia("Fundamentos de Dibujo","SAB-1317"),
                Materia("Química Aplicada","SAC-1331"),
            ))
        }
    }

    class SegundoSemestre{
        companion object Materias{
            var lista =  Semestre(1, mutableListOf(
                Materia("Cálculo Diferencial","ACF-0901"),
                Materia("Taller de Ética","ACA-0907"),
                Materia("Fundamentos de Investigación","ACC-0906"),
                Materia("Programación Básica","SAC-1330"),
                Materia("Fundamentos de Dibujo","SAB-1317"),
                Materia("Química Aplicada","SAC-1331"),
            ))
        }
    }

    class TercerSemestre{
        companion object Materias{
            var lista =  Semestre(1, mutableListOf(
                Materia("Cálculo Diferencial","ACF-0901"),
                Materia("Taller de Ética","ACA-0907"),
                Materia("Fundamentos de Investigación","ACC-0906"),
                Materia("Programación Básica","SAC-1330"),
                Materia("Fundamentos de Dibujo","SAB-1317"),
                Materia("Química Aplicada","SAC-1331"),
            ))
        }
    }

}

class MateriasAutomotrices
