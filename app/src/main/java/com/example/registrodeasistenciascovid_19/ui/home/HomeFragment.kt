package com.example.registrodeasistenciascovid_19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.util.DBUtil
import com.example.registrodeasistenciascovid_19.*
import com.example.registrodeasistenciascovid_19.ViewModels.CarreraViewModel
import com.example.registrodeasistenciascovid_19.databinding.FragmentHomeBinding
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.list.ListAdapter

class HomeFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private lateinit var mCarreraViewModel: CarreraViewModel

    var indiceCarrera: Int? = null

    lateinit var semestres: Array<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        mCarreraViewModel = ViewModelProvider(this).get(CarreraViewModel::class.java)
        if (IsEmpty(mCarreraViewModel)){
            Toast.makeText(context, "aaaa", Toast.LENGTH_LONG).show()
        }

        val adapter = ListAdapter()


        //mCarreraViewModel.AgregarCarrera(Carrera("a","Ing"))

        mCarreraViewModel.leerTodo.observe(viewLifecycleOwner, Observer { carrera ->
            adapter.setData(carrera)

            var arr = mutableListOf<String>()
            for (carreras in carrera){
                arr.add(carreras.nombreCarrera)
            }

            val adapter2 = context?.let { ArrayAdapter(it, R.layout.list_item, arr) }
            with(binding.cboCarreras){
                setAdapter(adapter2)
                onItemClickListener = this@HomeFragment
            }

        })

        return root
    }

    private fun IsEmpty(viewModel: CarreraViewModel): Boolean {

        var flag: Boolean = false
        viewModel.leerTodo.observe(viewLifecycleOwner, { carrera->
            flag = carrera.isNotEmpty()
        })

        return flag
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun CargarSemestresGeneral(){
        //Dropdown de semestres
        semestres = resources.getStringArray(R.array.Semestres)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }
        with(binding.cboSemestres){
            setAdapter(adapter)
        }
    }

    private fun CargarSemestresEspecialidad(){
        //Dropdown de semestres
        semestres = resources.getStringArray(R.array.SemestresEspecialidad)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }
        with(binding.cboSemestres){
            setAdapter(adapter)
        }
    }

    private fun CargarSemestresMaestrias(){
        //Dropdown de semestres
        semestres = resources.getStringArray(R.array.SemestresMaestria)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }
        with(binding.cboSemestres){
            setAdapter(adapter)
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        indiceCarrera = position

        if (position<=7)
            CargarSemestresGeneral()

        if (position==9)
            CargarSemestresEspecialidad()

        if (position==10)
            CargarSemestresMaestrias()
    }
}