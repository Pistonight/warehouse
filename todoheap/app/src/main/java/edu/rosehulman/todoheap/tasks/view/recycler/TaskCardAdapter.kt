package edu.rosehulman.todoheap.tasks.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.main.MainActivity
import edu.rosehulman.todoheap.databinding.CardTaskBinding
import edu.rosehulman.todoheap.common.view.RecyclerViewModelProvider
import edu.rosehulman.todoheap.tasks.view.TaskCardViewModel

class TaskCardAdapter(
        private val viewModelProvider: RecyclerViewModelProvider<TaskCardViewModel>,
        private val activity: MainActivity,
): RecyclerView.Adapter<TaskCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCardViewHolder {
        val binding = DataBindingUtil.inflate<CardTaskBinding>(
                LayoutInflater.from(activity),
                R.layout.card_task,
                parent,
                false
        )
        return TaskCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskCardViewHolder, position: Int) {
        holder.bind(viewModelProvider.getViewModel(position),activity.controller.taskController)
    }

    override fun getItemCount() = viewModelProvider.size
}