package com.example.registrodeasistenciascovid_19.ui.home

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_crear_clase.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
    private lateinit var localMateria: Materia
    private lateinit var localMaterias: List<Materia>



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val  responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult->
        if (activityResult.resultCode == RESULT_OK){
            Toast.makeText(context, "Si", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context, "No", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        _binding!!.btnQR1.setOnClickListener { iniciarScanner() }
        _binding!!.btnQR2.setOnClickListener { iniciarScanner() }

        _binding!!.btnCrearClase.setOnClickListener {
            val clase = GenerarClase()
            if (clase!=null) {
                mClasesViewModel.AgregarClase(clase)
                Toast.makeText(context, "Se registro la clase", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context, "No se registro la clase, verifique los datos", Toast.LENGTH_LONG).show()
            }
        }

        _binding!!.txtInicio.setText("${LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))}")

        CargarDuraciones()

        if (IsEmpty(mCarreraViewModel))
            Toast.makeText(context, "aaaa", Toast.LENGTH_LONG).show()
        else
            CargarCarreras()

        return root
    }

    private fun iniciarScanner() {
        IntentIntegrator(activity).initiateScan()
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment{ onTimeSelected(it) }
        fragmentManager?.let { timePicker.show(it, "time") }
    }

    private fun onTimeSelected(Time: String){
        txtInicio.setText("$Time")
    }

    private fun GenerarClase(): Clases? {

        if(VerficarEntradas()) {
            return Clases(
                Random.nextInt(0,100).toString(),
                localMateria.CodMateria,
                txtCodMaestro.text.toString(),
                txtCodAula.text.toString(),
                txtInicio.text.toString(),
                cboDuracion.text.toString().toInt(),
            )
        }
        else{
            return null
        }
    }

    private fun VerficarEntradas(): Boolean {
        if (txtCodAula.text.toString()=="") {
            Toast.makeText(context, "Porfavor ingrese el codigo del aula", Toast.LENGTH_LONG).show()
            return false
        }

        if (txtCodMaestro.text.toString()=="") {
            Toast.makeText(context, "Porfavor ingrese el codigo del docente", Toast.LENGTH_LONG).show()
            return false
        }

        if (cboCarreras.text.toString()=="") {
            Toast.makeText(context, "Porfavor seleccione una carrera", Toast.LENGTH_LONG).show()
            return false
        }

        if (cboSemestres.text.toString()=="") {
            Toast.makeText(context, "Porfavor seleccione un semestre", Toast.LENGTH_LONG).show()
            return false
        }

        if (cboMaterias.text.toString()=="") {
            Toast.makeText(context, "Porfavor seleccione una materia", Toast.LENGTH_LONG).show()
            return false
        }

        if (cboDuracion.text.toString()=="") {
            Toast.makeText(context, "Porfavor ingrese la duracion", Toast.LENGTH_LONG).show()
            return false
        }

        if (txtInicio.text.toString()=="") {
            Toast.makeText(context, "Porfavor seleccione una hora de inicio", Toast.LENGTH_LONG).show()
            return false
        }


        return true
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

            binding.cboMaterias.setOnItemClickListener { adapterView, view, position, l ->
                localMateria = localMaterias[position]
            }

        })
    }
    //endregion

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        localCarrera = GetCarrera(position)!!
        binding.cboSemestres.setText("")
        CargarSemestres(position)
    }
}