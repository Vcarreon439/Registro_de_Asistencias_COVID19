package com.example.registrodeasistenciascovid_19.ui.listaClases

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registrodeasistenciascovid_19.ClasesExternas.ClaseAdapter
import com.example.registrodeasistenciascovid_19.R
import com.example.registrodeasistenciascovid_19.ViewModels.ClasesViewModel
import com.example.registrodeasistenciascovid_19.database.LocalDatabase
import com.example.registrodeasistenciascovid_19.databinding.FragmentListaclasesBinding
import com.example.registrodeasistenciascovid_19.entities.Clases
import kotlinx.android.synthetic.*

class listaClasesFragment: Fragment(), ViewModelStoreOwner {

    private  lateinit var listaClasesViewModel: listaClasesViewModel
    private  lateinit var mClasesViewModel: ClasesViewModel
    private  lateinit var claseAdapter: ClaseAdapter


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
        mClasesViewModel = ViewModelProvider(this)[ClasesViewModel::class.java]
        _binding = FragmentListaclasesBinding.inflate(inflater, container, false)

        claseAdapter = ClaseAdapter { clase ->  adapterOnClick(clase)}

        listaClasesViewModel.listaClases.observe(viewLifecycleOwner) {
            it?.let {
                claseAdapter.submitList(it as MutableList<Clases>)
            }
        }

        return binding.root
    }

    private fun adapterOnClick(clase: Clases) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //recycler
        val recycler: RecyclerView = view.findViewById(R.id.listaClasesFragment)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        recycler.adapter = claseAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemClicked(clase: Clases) {
        Toast.makeText(context, "Kiubo",Toast.LENGTH_LONG).show()
    }

}