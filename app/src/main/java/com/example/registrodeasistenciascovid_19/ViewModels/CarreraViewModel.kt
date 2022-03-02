package com.example.registrodeasistenciascovid_19.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.registrodeasistenciascovid_19.database.LocalDatabase
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.repositories.CarreraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarreraViewModel(application: Application): AndroidViewModel(application) {

    val leerTodo: LiveData<List<Carrera>>

    private val repository: CarreraRepository

    init {
        val  carreraDao = LocalDatabase.getDatabase(application).carreraDao()
        repository = CarreraRepository(carreraDao)
        leerTodo = repository.readAll
    }

    fun AgregarCarrera(carrera: Carrera){
        viewModelScope.launch(Dispatchers.IO) {
            repository.agregarCarrera(carrera)
        }
    }

}