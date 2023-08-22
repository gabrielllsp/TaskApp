package com.gabriel.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.taskapp.R
import com.gabriel.taskapp.databinding.FragmentFormTaskBinding
import com.gabriel.taskapp.util.initToolbar

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }
    private fun initListeners(){

        binding.btnSave.setOnClickListener {
            validate()
        }
    }
    private fun validate(){
        val description = binding.edtDescription.text.toString().trim()

        if (description.isNotEmpty()){
            Toast.makeText(requireContext(), "Tudo certo.", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "Preencha uma descrição.", Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}