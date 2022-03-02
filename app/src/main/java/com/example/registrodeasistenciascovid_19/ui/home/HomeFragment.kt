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
import com.example.registrodeasistenciascovid_19.*
import com.example.registrodeasistenciascovid_19.ViewModels.CarreraViewModel
import com.example.registrodeasistenciascovid_19.ViewModels.MateriaViewModel
import com.example.registrodeasistenciascovid_19.databinding.FragmentHomeBinding
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.entities.Materia

class HomeFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    //ViewModels
    private lateinit var mCarreraViewModel: CarreraViewModel
    private lateinit var mMateriaViewModel: MateriaViewModel

    //localData
    private lateinit var localCarrera: Carrera
    private lateinit var localMaterias: List<Materia>

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

        mCarreraViewModel = ViewModelProvider(this)[CarreraViewModel::class.java]
        mMateriaViewModel = ViewModelProvider(this).get(MateriaViewModel::class.java)

        if (IsEmpty(mCarreraViewModel)){
            //Toast.makeText(context, "aaaa", Toast.LENGTH_LONG).show()
        }
        else
            CargarCarreras()

        //val adapterMateria = MateriaListAdapter()

        return root
    }



    private fun GetCarrera(position: Int): Carrera?{
        var temp: Carrera? = null
        mCarreraViewModel.leerTodo.observe(viewLifecycleOwner, Observer { carrera -> temp = carrera[position] })
        return temp
    }

    private fun IsEmpty(viewModel: CarreraViewModel): Boolean {

        var flag: Boolean = false
        viewModel.leerTodo.observe(viewLifecycleOwner) { carrera ->
            flag = carrera.isNotEmpty()
        }

        return flag
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //region CargarDatos
    private fun CargarCarreras(){
        mCarreraViewModel.leerTodo.observe(viewLifecycleOwner, Observer { carrera ->

            var arr = mutableListOf<String>()
            for (carreras in carrera){
                arr.add(carreras.nombreCarrera)
            }

            val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, arr) }
            with(binding.cboCarreras){
                setAdapter(adapter)
                onItemClickListener = this@HomeFragment
            }
        })
    }
    private fun CargarSemestres(position: Int){
        var semestres: Array<String> = emptyArray()

        if (position<=7)
            semestres = resources.getStringArray(R.array.Semestres)

        if (position==9)
            semestres = resources.getStringArray(R.array.SemestresEspecialidad)

        if (position==10)
            semestres = resources.getStringArray(R.array.SemestresMaestria)

        //Dropdown de semestres
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }
        with(binding.cboSemestres){
            setAdapter(adapter)
        }
        
        binding.cboSemestres.setOnItemClickListener { adapterView, view, position, l ->
            CargarMaterias(localCarrera, position+1)
        }
        
    }
    private fun CargarMaterias(carrera: Carrera, numSemestre: Int){
        mMateriaViewModel.materiasSemestre(carrera.idCarrera, numSemestre).observe(viewLifecycleOwner, Observer { materias ->
            localMaterias = materias
            var arr = mutableListOf<String>()

            for (materia in materias )
                arr.add(materia.nombreMateria)

            val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, arr) }
            binding.cboMaterias.setAdapter(adapter)
        })
    }
    //endregion

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        localCarrera = GetCarrera(position)!!
        CargarSemestres(position)
    }
}