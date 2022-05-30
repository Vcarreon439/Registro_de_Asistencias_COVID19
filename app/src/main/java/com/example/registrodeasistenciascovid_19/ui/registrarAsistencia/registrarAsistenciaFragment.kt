package com.example.registrodeasistenciascovid_19.ui.registrarAsistencia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.registrodeasistenciascovid_19.databinding.FragmentRegistrarClaseBinding

class registrarAsistenciaFragment : Fragment() {

    private lateinit var galleryViewModel: registrarAsistenciaViewModel
    private var _binding: FragmentRegistrarClaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(registrarAsistenciaViewModel::class.java)

        _binding = FragmentRegistrarClaseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}