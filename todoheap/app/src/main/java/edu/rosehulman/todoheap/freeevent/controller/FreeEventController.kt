package edu.rosehulman.todoheap.freeevent.controller

import edu.rosehulman.todoheap.freeevent.FreeEventActivity
import edu.rosehulman.todoheap.common.ControllerBase

class FreeEventController(
    private val activity: FreeEventActivity
): ControllerBase(activity) {
    fun selectDeadlineDate(){
        promptDate (activity.model.year, activity.model.month, activity.model.dayOfMonth){ year, month, dayOfMonth ->
            activity.model.apply {
                this.year = year
                this.month = month
                this.dayOfMonth = dayOfMonth
                activity.binding.model = this
            }
        }
    }
    fun selectDeadlineTime(){
        promptTime (activity.model.hour, activity.model.minute, ){ hour, minute ->
            activity.model.apply {
                this.hour = hour
                this.minute = minute
                activity.binding.model = this
            }
        }
    }

    fun updateView(): FreeEventController {
        activity.binding.model = activity.model
        return this
    }
}