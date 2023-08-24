package com.gabriel.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.taskapp.R
import com.gabriel.taskapp.data.model.Status
import com.gabriel.taskapp.data.model.Task
import com.gabriel.taskapp.databinding.FragmentTodoBinding
import com.gabriel.taskapp.ui.adapter.TaskAdapter


class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerView(getTasks())
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerView(taskList: List<Task>) {
        taskAdapter = TaskAdapter(requireContext(), taskList) { task, option ->
            optionSelected(task, option)
        }

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.adapter = taskAdapter
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVE -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_LONG)
                    .show()
            }

            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_LONG)
                    .show()
            }

            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_LONG)
                    .show()
            }

            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Next ${task.description}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun getTasks() = listOf(
        Task("0", "Criar nova tela de login", Status.TODO),
        Task("1", "Validar informações na tela de login", Status.TODO),
        Task("2", "Adicionar nova funcionalidade no app", Status.TODO),
        Task("3", "salvar token no localmente", Status.TODO),
        Task("4", "Criar funcionalidade de logout no app", Status.TODO)
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}