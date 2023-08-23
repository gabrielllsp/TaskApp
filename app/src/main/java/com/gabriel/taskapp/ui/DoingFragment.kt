package com.gabriel.taskapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.taskapp.data.model.Status
import com.gabriel.taskapp.data.model.Task
import com.gabriel.taskapp.databinding.FragmentDoingBinding
import com.gabriel.taskapp.ui.adapter.TaskAdapter


class DoingFragment : Fragment() {

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
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
        Task("0", "Criar nova tela de login", Status.DOING),
        Task("1", "Validar informações na tela de login", Status.DOING),
        Task("2", "Adicionarnova funcionalidade no app", Status.DOING),
        Task("3", "salvar token no localmente", Status.DOING),
        Task("4", "Criar funcionalidade de logout no app", Status.DOING)
    )



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}