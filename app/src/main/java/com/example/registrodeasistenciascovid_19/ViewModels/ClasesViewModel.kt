package com.example.registrodeasistenciascovid_19.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.registrodeasistenciascovid_19.Dao.ClasesDao
import com.example.registrodeasistenciascovid_19.database.LocalDatabase
import com.example.registrodeasistenciascovid_19.entities.Clases
import com.example.registrodeasistenciascovid_19.entities.Materia
import com.example.registrodeasistenciascovid_19.repositories.ClasesRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClasesViewModel(aplication: Application) :AndroidViewModel(aplication) {

    //val leerTodo: LiveData<List<Clases>>
    private val repository: ClasesRepository

    init {
        val claseDao = LocalDatabase.getDatabase(aplication).clasesDao()
        repository = ClasesRepository(claseDao)
        //leerTodo = repository.readAll
    }

    fun AgregarClase(clase: Clases){
        viewModelScope.launch(Dispatchers.IO) {
            repository.agregarClase(clase)
        }
    }
}