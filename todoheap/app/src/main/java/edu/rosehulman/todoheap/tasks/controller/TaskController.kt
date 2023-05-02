package edu.rosehulman.todoheap.tasks.controller

import android.content.Intent
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.common.ControllerBase
import edu.rosehulman.todoheap.data.Database
import edu.rosehulman.todoheap.freeevent.FreeEventActivity
import edu.rosehulman.todoheap.main.MainActivity
import edu.rosehulman.todoheap.tasks.model.TaskPageModel

class TaskController(
    val activity: MainActivity,
    val model:TaskPageModel,
): ControllerBase(activity) {
    fun deleteAt(position: Int) {
        this.promptConfirm(R.string.title_dialog_confirm,R.string.confirm_delete,{
            Database.freeEventsCollection?.document(model[position].id?:"")?.delete()
        }){
            model.recyclerAdapter?.notifyItemChanged(position)
        }
    }

    fun editAt(position: Int) {
        val event = activity.app.taskPageModel[position]
        val intent = Intent(activity, FreeEventActivity::class.java)
                .putExtra(Constants.KEY_FREE_EVENT_ID, event.id)
                .putExtra(Constants.KEY_FREE_EVENT, event)
                .putExtra(Constants.KEY_SET_TITLE,activity.resources.getString(R.string.title_edit_free_event))
        activity.startActivityForResult(intent, Constants.RC_EDIT_FREE_EVENT)

    }

    fun add(){
        val intent = Intent(activity, FreeEventActivity::class.java)
                .putExtra(Constants.KEY_SET_TITLE,activity.resources.getString(R.string.title_add_free_event))
        activity.startActivityForResult(intent, Constants.RC_ADD_FREE_EVENT)
    }


}