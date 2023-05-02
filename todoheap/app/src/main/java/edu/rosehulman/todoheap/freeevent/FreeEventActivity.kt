package edu.rosehulman.todoheap.freeevent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.freeevent.controller.FreeEventController
import edu.rosehulman.todoheap.databinding.ActivityFreeEventBinding
import edu.rosehulman.todoheap.freeevent.input.FreeEventInputModel
import edu.rosehulman.todoheap.common.model.FreeEvent

class FreeEventActivity: AppCompatActivity() {

    lateinit var binding: ActivityFreeEventBinding
    lateinit var controller: FreeEventController
    lateinit var model: FreeEventInputModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_free_event)
        controller = FreeEventController(this)
        binding.controller = controller
        initToolbar()
        initModel()
        initFields()

        binding.inputEventName.requestFocus()
    }

    private fun initToolbar() {
        intent?.getStringExtra(Constants.KEY_SET_TITLE)?.let(binding.toolbar::setTitle)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initModel(){
        model = FreeEventInputModel()
        model.setDeadlineToCurrentTime()
        if(intent!=null){
            val event = intent.getParcelableExtra<FreeEvent>(Constants.KEY_FREE_EVENT)
            event?.let(model::copyFromEvent)
        }

        binding.model = model
    }


    private fun initFields() {
        initSpinner(binding.enjoyablilitySpinner, R.array.form_enjoyability_options)
        initSpinner(binding.procrastinationSpinner, R.array.form_procrastination_options)
        initSpinner(binding.prioritySpinner,R.array.form_priority_options)

    }

    private fun initSpinner(spinner: Spinner, resId: Int){
        ArrayAdapter.createFromResource(
            this,
            resId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_event, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_save -> {
                save()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun save() {
        val errorId = model.validateInput()
        if(errorId!=null){
            controller.showAlert(R.string.title_dialog_error,errorId)
            return
        }
        val newEvent = model.toEvent()

        Log.d("EventDebug", "New Event Added: $newEvent")

        val id = intent.getStringExtra(Constants.KEY_FREE_EVENT_ID)

        val returnIntent = Intent().putExtra(Constants.KEY_FREE_EVENT, newEvent)
        if(id!=null) returnIntent.putExtra(Constants.KEY_FREE_EVENT_ID,id)


        setResult(Constants.RESULT_GOOD, returnIntent)
        finish()

    }

}