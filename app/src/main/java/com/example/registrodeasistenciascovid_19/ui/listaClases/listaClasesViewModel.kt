package com.example.registrodeasistenciascovid_19.ui.listaClases

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.*
import androidx.room.Database
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.ViewModels.CarreraViewModel
import com.example.registrodeasistenciascovid_19.ViewModels.ClasesViewModel
import com.example.registrodeasistenciascovid_19.database.LocalDatabase
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.entities.Clases
import com.example.registrodeasistenciascovid_19.repositories.ClasesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.sql.CommonDataSource
import javax.sql.DataSource

class listaClasesViewModel(application: Application): AndroidViewModel(application) {

    var listaClases: LiveData<List<Clases>>
    private val repository: ClasesRepository
    init {
        val dao = LocalDatabase.getDatabase(application).clasesDao()
        repository = ClasesRepository(dao)
        listaClases = repository.clasesInternas
    }

}