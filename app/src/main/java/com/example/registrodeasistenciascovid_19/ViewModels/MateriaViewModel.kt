package com.example.registrodeasistenciascovid_19.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.registrodeasistenciascovid_19.database.LocalDatabase
import com.example.registrodeasistenciascovid_19.entities.Materia
import com.example.registrodeasistenciascovid_19.repositories.MateriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MateriaViewModel(application: Application): AndroidViewModel(application) {

    val leerTodo: LiveData<List<Materia>>
    private val repository: MateriaRepository

    init {
        val materiaDao = LocalDatabase.getDatabase(application).materiaDao()
        repository = MateriaRepository(materiaDao)
        leerTodo = repository.readAll
    }

    fun AgregarMateria(materia: Materia){
        viewModelScope.launch(Dispatchers.IO) {
            repository.agregarMateria(materia)
        }
    }

    fun materiasSemestre(idCarrera: String, numSemestre: Int): LiveData<List<Materia>>{
        return repository.materiasSemestre(idCarrera, numSemestre)
    }

}