package com.example.registrodeasistenciascovid_19.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.registrodeasistenciascovid_19.*
import com.example.registrodeasistenciascovid_19.ClasesExternas.TimePickerFragment
import com.example.registrodeasistenciascovid_19.ViewModels.CarreraViewModel
import com.example.registrodeasistenciascovid_19.ViewModels.ClasesViewModel
import com.example.registrodeasistenciascovid_19.ViewModels.MateriaViewModel
import com.example.registrodeasistenciascovid_19.databinding.FragmentCrearClaseBinding
import com.example.registrodeasistenciascovid_19.entities.Carrera
import com.example.registrodeasistenciascovid_19.entities.Clases
import com.example.registrodeasistenciascovid_19.entities.Materia
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_crear_clase.*
import java.util.*
import kotlin.random.Random

class HomeFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentCrearClaseBinding? = null

    //ViewModels
    private lateinit var mCarreraViewModel: CarreraViewModel
    private lateinit var mMateriaViewModel: MateriaViewModel
    private lateinit var mClasesViewModel: ClasesViewModel

    //localData
    private lateinit var localCarrera: Carrera
    private lateinit var localMaterias: List<Materia>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var txtCodeAula: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        mCarreraViewModel = ViewModelProvider(this)[CarreraViewModel::class.java]
        mMateriaViewModel = ViewModelProvider(this)[MateriaViewModel::class.java]
        mClasesViewModel = ViewModelProvider(this)[ClasesViewModel::class.java]

        _binding = FragmentCrearClaseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Set Click Listeners
        _binding!!.txtInicio.setOnClickListener{ showTimePickerDialog() }

        _binding!!.btnQR1.setOnClickListener {
            val scanner = IntentIntegrator(this.activity).initiateScan()
        }

        _binding!!.btnQR2.setOnClickListener {
            var scanner = IntentIntegrator(this.activity).initiateScan()
        }

        _binding!!.btnCrearClase.setOnClickListener { mClasesViewModel.AgregarClase(GenerarClase()) }

        _binding!!.txtInicio.setText("${Calendar.HOUR_OF_DAY}:${Calendar.MINUTE}")


        CargarDuraciones()

        if (IsEmpty(mCarreraViewModel))
            Toast.makeText(context, "aaaa", Toast.LENGTH_LONG).show()
        else
            CargarCarreras()

        return root
    }



    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment{ onTimeSelected(it) }
        fragmentManager?.let { timePicker.show(it, "time") }
    }

    private fun onTimeSelected(Time: String){
        txtInicio.setText("$Time")
    }

    private fun GenerarClase(): Clases {
        return Clases(Random.nextInt(0,100).toString(),txtCodMaestro.text.toString(),txtCodAula.text.toString(),"12:00",1)
    }

    private fun GetCarrera(position: Int): Carrera?{
        var temp: Carrera? = null
        mCarreraViewModel.leerTodo.observe(viewLifecycleOwner, Observer { carrera -> temp = carrera[position] })
        return temp
    }

    private fun IsEmpty(viewModel: CarreraViewModel): Boolean {
        var flag: Boolean = false
        viewModel.leerTodo.observe(viewLifecycleOwner) { carrera -> flag = carrera.isNotEmpty() }
        return flag
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //region CargarDatos
    private fun CargarCarreras(){
        mCarreraViewModel.leerTodo.observe(viewLifecycleOwner, Observer { carrera ->
            homeViewModel.listaCarreras = carrera
            with(binding.cboCarreras){
                setAdapter(homeViewModel.adapterCarreras(context))
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
            binding.cboMaterias.setText("")
            CargarMaterias(localCarrera, position+1)
        }
        
    }

    private fun CargarDuraciones(){
        with(binding.cboDuracion){
            setAdapter(homeViewModel.adapterDuracion(context))
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
        binding.cboSemestres.setText("")
        CargarSemestres(position)
    }
}