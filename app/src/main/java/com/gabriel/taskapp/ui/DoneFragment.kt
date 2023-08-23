package com.gabriel.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.taskapp.data.model.Status
import com.gabriel.taskapp.data.model.Task
import com.gabriel.taskapp.databinding.FragmentDoneBinding
import com.gabriel.taskapp.ui.adapter.TaskAdapter


class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(getTasks())

    }
    private fun initRecyclerView(taskList: List<Task>){
        taskAdapter = TaskAdapter(requireContext(),taskList)

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.setHasFixedSize(true)
        binding.rvTasks.adapter = taskAdapter
    }

    private fun getTasks() = listOf(
        Task("0", "Criar nova tela de login", Status.DONE),
        Task("1", "Validar informações na tela de login", Status.DONE),
        Task("2", "Adicionarnova funcionalidade no app", Status.DONE),
        Task("3", "salvar token no localmente", Status.DONE),
        Task("4", "Criar funcionalidade de logout no app", Status.DONE)
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}