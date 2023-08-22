package com.gabriel.taskapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.taskapp.R
import com.gabriel.taskapp.databinding.FragmentRegisterBinding
import com.gabriel.taskapp.util.initToolbar


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners(){

        binding.btnRegister.setOnClickListener {validate()}
    }

    private fun validate(){
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (email.isNotEmpty()){
            if (password.isNotEmpty()){
                Toast.makeText(requireContext(), "Tudo certo.", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Preencha uma senha.", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(requireContext(), "Preencha um e-mail válido.", Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}