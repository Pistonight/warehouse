package edu.rosehulman.todoheap.tasks.view.recycler

import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.todoheap.calendar.controller.CalendarCardController
import edu.rosehulman.todoheap.calendar.view.CalendarCardViewModel
import edu.rosehulman.todoheap.databinding.CardCalendarBinding
import edu.rosehulman.todoheap.main.Controller
import edu.rosehulman.todoheap.tasks.controller.TaskCardController
import edu.rosehulman.todoheap.databinding.CardTaskBinding
import edu.rosehulman.todoheap.tasks.view.TaskCardViewModel

class CalendarCardViewHolder(private val binding: CardCalendarBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: CalendarCardViewModel, controller: Controller) {
       binding.model = viewModel
        binding.controller = CalendarCardController(controller.calendarController){adapterPosition}
    }
}