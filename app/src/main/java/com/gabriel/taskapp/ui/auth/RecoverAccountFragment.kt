package com.gabriel.taskapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gabriel.taskapp.R
import com.gabriel.taskapp.databinding.FragmentRecoverAccountBinding
import com.gabriel.taskapp.util.initToolbar
import com.gabriel.taskapp.util.showBottomSheet


class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    private fun initListeners(){

        binding.btnRecover.setOnClickListener {validate()}
    }
    private fun validate(){
        val email = binding.edtEmail.text.toString().trim()

        if (email.isNotEmpty()){
            Toast.makeText(requireContext(), "Tudo certo.", Toast.LENGTH_LONG).show()
        }else{
            showBottomSheet(message = getString(R.string.email_empty))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}