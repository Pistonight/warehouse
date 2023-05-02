package edu.rosehulman.todoheap.main

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.calendar.controller.CalendarController
import edu.rosehulman.todoheap.freeevent.FreeEventActivity
import edu.rosehulman.todoheap.databinding.RadioEventTypeBinding
import edu.rosehulman.todoheap.scheduledevent.ScheduledEventActivity
import edu.rosehulman.todoheap.tasks.controller.TaskController

class Controller(private val activity: MainActivity) {

    private lateinit var dialog: AlertDialog
    val taskController: TaskController = TaskController(activity, activity.app.taskPageModel)
    val calendarController: CalendarController = CalendarController(activity, activity.app.calendarPageModel)

    fun onClickFab() {

        Log.d("EventDebug", "Makes an Event")
        val builder = AlertDialog.Builder(activity)

        //Inflate the view and bind the data
        val bind = DataBindingUtil.inflate<RadioEventTypeBinding>(LayoutInflater.from(activity), R.layout.radio_event_type, null, false)
        bind.controller = this
        builder.setView(bind.root)
        dialog = builder.create()
        dialog.show()
    }

    fun onClickFree() {
        taskController.add()
        dialog.dismiss()
    }

    fun onClickScheduled() {
        calendarController.add();
        dialog.dismiss()
    }





}