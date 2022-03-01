package com.example.registrodeasistenciascovid_19.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registrodeasistenciascovid_19.Carrera

class HomeViewModel : ViewModel() {

    val listaCarreras = mutableListOf<Carrera>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}