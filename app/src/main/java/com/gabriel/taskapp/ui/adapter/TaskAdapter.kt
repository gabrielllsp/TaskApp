package com.gabriel.taskapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.taskapp.R
import com.gabriel.taskapp.data.model.Status
import com.gabriel.taskapp.data.model.Task
import com.gabriel.taskapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>
) : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]

        holder.binding.textDescription.text = task.description
        setIndicators(task, holder)
    }

    private fun setIndicators(task: Task, holder: MyViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.btnBack.isVisible = false
                holder.binding.btnNext.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_default
                    )
                )
            }

            Status.DOING -> {
                holder.binding.btnBack.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_status_todo
                    )
                )
                holder.binding.btnNext.setColorFilter(
                    ContextCompat.getColor(
                        context, R.color.color_status_done
                    )
                )
            }

            Status.DONE -> {
                holder.binding.btnNext.isVisible = false
                holder.binding.btnBack.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.color_default
                    )
                )
            }
        }
    }

    inner class MyViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)
}