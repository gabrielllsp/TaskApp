package com.gabriel.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.taskapp.R
import com.gabriel.taskapp.data.model.Status
import com.gabriel.taskapp.data.model.Task
import com.gabriel.taskapp.databinding.FragmentFormTaskBinding
import com.gabriel.taskapp.util.initToolbar
import com.gabriel.taskapp.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var task: Task
    private var status: Status = Status.TODO
    private var newTask: Boolean = true
    private lateinit var auth: FirebaseAuth

    private lateinit var reference: DatabaseReference

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
        reference = Firebase.database.reference
        auth = Firebase.auth
        initListeners()
    }

    private fun initListeners() {

        binding.btnSave.setOnClickListener {
            validate()
        }
        binding.rgStatus.setOnCheckedChangeListener { _, i ->
            status = when (id) {
                R.id.rbTodo -> Status.TODO
                R.id.rbDoing -> Status.DOING
                else -> Status.DONE
            }
        }
    }

    private fun validate() {
        val description = binding.edtDescription.text.toString().trim()

        if (description.isNotEmpty()) {

            binding.progressBar.isVisible = true

            if (newTask) task = Task()
            task.id = reference.database.reference.push().key ?: ""
            task.description = description
            task.status = status
            saveTask()

        } else {
            showBottomSheet(message = getString(R.string.description_empty_form_task_fragment))
        }
    }

    private fun saveTask() {
        reference
            .child("tasks")
            .child(auth.currentUser?.uid ?: "")
            .child(task.id)
            .setValue(task).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        R.string.text_save_sucess_form_task_fragment,
                        Toast.LENGTH_LONG
                    ).show()

                    if (newTask) {// Nova Tarefa
                        findNavController().popBackStack()
                    } else {//Editando tarefa
                        binding.progressBar.isVisible = false
                    }

                } else {
                    binding.progressBar.isVisible = false
                    showBottomSheet(message = getString(R.string.error_generic))
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}