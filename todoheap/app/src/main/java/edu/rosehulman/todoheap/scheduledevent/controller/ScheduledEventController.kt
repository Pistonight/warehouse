package edu.rosehulman.todoheap.scheduledevent.controller

import edu.rosehulman.todoheap.common.ControllerBase
import edu.rosehulman.todoheap.scheduledevent.ScheduledEventActivity

class ScheduledEventController(
        val activity: ScheduledEventActivity
):ControllerBase(activity) {
    fun selectStartDate(){
        this.promptDate(activity.model.startYear, activity.model.startMonth,activity.model.startDay) { year, month, day ->
            activity.model.startYear = year
            activity.model.startMonth = month
            activity.model.startDay = day
            activity.binding.model = activity.model
        }
    }
    fun selectStartTime(){
        this.promptTime(activity.model.startHour, activity.model.startMinute) { hour, minute ->
            activity.model.startHour = hour
            activity.model.startMinute = minute
            activity.binding.model = activity.model
        }
    }
    fun selectEndDate(){
        this.promptDate(activity.model.endYear, activity.model.endMonth,activity.model.endDay) { year, month, day ->
            activity.model.endYear = year
            activity.model.endMonth = month
            activity.model.endDay = day
            activity.binding.model = activity.model
        }
    }
    fun selectEndTime(){
        this.promptTime(activity.model.endHour, activity.model.endMinute) { hour, minute ->
            activity.model.endHour = hour
            activity.model.endMinute = minute
            activity.binding.model = activity.model
        }
    }
}