package com.example.registrodeasistenciascovid_19.ui.listaClases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import com.example.registrodeasistenciascovid_19.ClasesExternas.ClaseAdapter
import com.example.registrodeasistenciascovid_19.ClasesExternas.IClasesRVAdapter
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.databinding.FragmentListaclasesBinding
import com.example.registrodeasistenciascovid_19.entities.Clases

class listaClasesFragment: Fragment(), ViewModelStoreOwner, IClasesRVAdapter {

    private  lateinit var listaClasesViewModel: listaClasesViewModel
    private var _binding: FragmentListaclasesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        listaClasesViewModel = ViewModelProvider(this)[com.example.registrodeasistenciascovid_19.ui.listaClases.listaClasesViewModel::class.java]
        _binding = FragmentListaclasesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(clase: Clases) {
        Toast.makeText(context, "Kiubo",Toast.LENGTH_LONG).show()
    }

}