package edu.rosehulman.todoheap.common

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.databinding.DialogInputDateBinding
import edu.rosehulman.todoheap.databinding.DialogInputTimeBinding
import edu.rosehulman.todoheap.common.input.DateInputModel
import edu.rosehulman.todoheap.common.input.TimeInputModel

abstract class ControllerBase(
    private val activity: AppCompatActivity
) {
    fun hideKeyboardOnLoseFocus(view: View, hasFocus: Boolean) {
        hideKeyboardOnFocusEqual(view, hasFocus, false)
    }

    fun hideKeyboardOnGainFocus(view: View, hasFocus: Boolean) {
        hideKeyboardOnFocusEqual(view, hasFocus, true)
    }

    private fun hideKeyboardOnFocusEqual(view: View, hasFocus: Boolean, hideOnHasFocus: Boolean) {
        if(hasFocus==hideOnHasFocus) {
           hideKeyboard(view)
        }
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = ContextCompat.getSystemService(activity, InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun promptDate(initYear: Int, initMonth: Int, initDay: Int, callback: (year: Int, month: Int, dayOfMonth: Int)->Unit) {
        val binding = DataBindingUtil.inflate<DialogInputDateBinding>(activity.layoutInflater, R.layout.dialog_input_date, null, false)
        val model = DateInputModel(initYear, initMonth, initDay)
        binding.model = model
        AlertDialog.Builder(activity)
            .setTitle(R.string.title_dialog_date)
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok){ _ , _->
                callback(model.year, model.month,model.dayOfMonth)
            }.setNegativeButton(android.R.string.cancel){ _ , _->
            }.create()
            .show()
    }

    fun promptTime(initHour: Int, initMinute: Int, callback: (hour: Int, minute: Int)->Unit) {
        val binding = DataBindingUtil.inflate<DialogInputTimeBinding>(activity.layoutInflater, R.layout.dialog_input_time, null, false)
        val model = TimeInputModel(initHour, initMinute)
        binding.model = model
        AlertDialog.Builder(activity)
            .setTitle(R.string.title_dialog_time)
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok){ _ , _->
                callback(model.hour, model.minute)
            }.setNegativeButton(android.R.string.cancel){ _ , _->
            }.create()
            .show()
    }

    fun showAlert(title: Int, content: Int){
        AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(content)
            .setPositiveButton(android.R.string.ok){ _ , _->
            }.create()
            .show()
    }

    fun promptConfirm(title: Int, content: Int, callback:()->Unit) = promptConfirm(title,content,callback,null)

    fun promptConfirm(title: Int, content: Int, callback:()->Unit,cancel:(()->Unit)?){
        AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(android.R.string.ok){ _ , _->
                    callback()
                }.setNegativeButton(android.R.string.cancel){ _, _->
                    cancel?.invoke()
                }.create()
                .show()
    }

}