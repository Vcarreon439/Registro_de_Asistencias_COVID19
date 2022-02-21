package com.example.registrodeasistenciascovid_19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.registrodeasistenciascovid_19.*
import com.example.registrodeasistenciascovid_19.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    lateinit var carreras: Array<String>
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
A
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        carreras = resources.getStringArray(R.array.Carreras)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, carreras) }

        with(binding.cboCarreras){
            setAdapter(adapter)
            onItemClickListener = this@HomeFragment
        }

        binding.btnCrearClase.setOnClickListener {
            Toast.makeText(context, "Hola", Toast.LENGTH_LONG).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun CargarSemestresGeneral(){
        semestres = resources.getStringArray(R.array.Semestres)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }

        with(binding.cboSemestres){
            setAdapter(adapter)
        }

        binding.cboSemestres.setOnItemClickListener(
            AdapterView.OnItemClickListener(
                
            )
        )
    }

    private fun CargarSemestresEspecialidad(){
        semestres = resources.getStringArray(R.array.SemestresEspecialidad)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }

        with(binding.cboSemestres){
            setAdapter(adapter)
        }
    }

    private fun CargarSemestresMaestrias(){
        semestres = resources.getStringArray(R.array.SemestresMaestria)
        val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, semestres) }

        with(binding.cboSemestres){
            setAdapter(adapter)
        }

    }

    private fun ConvertirArreglo(mutableList: MutableList<Materia>): MutableList<String> {
        var retorno: MutableList<String> = mutableListOf()

        for (item in mutableList){
            retorno.add(item.nombre)
        }

        return retorno
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString();

        if (position<=7)
            CargarSemestresGeneral()

        if (position==8)
            CargarSemestresEspecialidad()

        if (position==9)
            CargarSemestresMaestrias()


            //val adapter = context?.let { ArrayAdapter(it, R.layout.list_item, ConvertirArreglo(SemestresAutomotrices.PrimerSemestre.Materias.listaMaterias)) }

            /*with(binding.cboMaterias){
                setAdapter(adapter)
            }*/
    }
}