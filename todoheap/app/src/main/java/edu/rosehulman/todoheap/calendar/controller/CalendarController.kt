package edu.rosehulman.todoheap.calendar.controller

import android.content.Intent
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.calendar.model.CalendarPageModel
import edu.rosehulman.todoheap.common.ControllerBase
import edu.rosehulman.todoheap.data.Database
import edu.rosehulman.todoheap.freeevent.FreeEventActivity
import edu.rosehulman.todoheap.main.MainActivity
import edu.rosehulman.todoheap.scheduledevent.ScheduledEventActivity

class CalendarController(
        val activity: MainActivity,
        val model: CalendarPageModel,
):ControllerBase(activity) {
    fun onPressPrevious(){
        model.previousDay()
    }
    fun onPressNext(){
        model.nextDay()
    }
    fun onPressSelectDate(){
        this.promptDate(model.selectedYear, model.selectedMonth,model.selectedDay){ year, month, dayOfMonth ->
            model.selectDay(year,month,dayOfMonth)
        }
    }
    fun deleteAt(position: Int){
        this.promptConfirm(R.string.title_dialog_confirm, R.string.confirm_delete,{
            Database.scheduledEventsCollection?.document(model[position].id?:"")?.delete()
        }){
            model.recyclerAdapter?.notifyItemChanged(position)
        }
    }
    fun editAt(position: Int) {
        val event = activity.app.calendarPageModel[position]
        val intent = Intent(activity, ScheduledEventActivity::class.java)
                .putExtra(Constants.KEY_SCHEDULED_EVENT_ID, event.id)
                .putExtra(Constants.KEY_SCHEDULED_EVENT, event)
                .putExtra(Constants.KEY_SET_TITLE,activity.resources.getString(R.string.title_edit_scheduled_event))
        activity.startActivityForResult(intent, Constants.RC_EDIT_SCHEDULED_EVENT)
    }
    fun add(){
        val intent = Intent(activity, ScheduledEventActivity::class.java)
                .putExtra(Constants.KEY_SET_TITLE,activity.resources.getString(R.string.title_add_scheduled_event))
        activity.startActivityForResult(intent, Constants.RC_ADD_SCHEDULED_EVENT)

    }


}