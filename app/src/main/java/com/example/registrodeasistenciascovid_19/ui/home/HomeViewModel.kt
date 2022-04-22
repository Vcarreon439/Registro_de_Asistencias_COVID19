package com.example.registrodeasistenciascovid_19.ui.home

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.*
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.ViewModels.CarreraViewModel
import com.example.registrodeasistenciascovid_19.entities.Carrera

class HomeViewModel : ViewModel(), ViewModelStoreOwner {

    var listaCarreras: List<Carrera> = emptyList()
    lateinit var mCarreraViewModel: CarreraViewModel

    fun adapterCarreras(context: Context): ArrayAdapter<String>{

        var arr = mutableListOf<String>()

        for (carreras in this.listaCarreras){
            arr.add(carreras.nombreCarrera)
        }

        return context?.let { ArrayAdapter(it, R.layout.list_item, arr) }
    }

    fun adapterDuracion(context: Context): ArrayAdapter<String>{
        var arr = mutableListOf<String>()
        arr.add("1")
        arr.add("2")
        return context?.let { ArrayAdapter(it, R.layout.list_item, arr) }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }
}