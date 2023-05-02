package edu.rosehulman.todoheap.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import edu.rosehulman.todoheap.main.MainActivity
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.tasks.controller.TaskCardSwipeCallback
import edu.rosehulman.todoheap.databinding.FragmentTasksBinding
import edu.rosehulman.todoheap.tasks.view.recycler.CalendarCardAdapter
import edu.rosehulman.todoheap.tasks.view.recycler.TaskCardAdapter

class TasksFragment : Fragment() {

    private lateinit var activity: MainActivity

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTasksBinding>(inflater,R.layout.fragment_tasks,container,false)
        activity = requireContext() as MainActivity
        //setup recycler
        binding.recyclerTasks.let{
            it.layoutManager = LinearLayoutManager(activity)
            val taskCardAdapter = TaskCardAdapter(activity.app.taskPageModel, activity)
            activity.app.taskPageModel.recyclerAdapter = taskCardAdapter
            it.adapter = taskCardAdapter
            it.setHasFixedSize(true)
        }

        ItemTouchHelper(TaskCardSwipeCallback(activity.controller.taskController)).attachToRecyclerView(binding.recyclerTasks)

        return binding.root
    }
}