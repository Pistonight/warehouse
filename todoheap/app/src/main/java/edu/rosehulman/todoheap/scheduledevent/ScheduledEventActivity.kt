package edu.rosehulman.todoheap.scheduledevent

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.Constants.KEY_SCHEDULED_EVENT
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.common.model.ScheduledEvent
import edu.rosehulman.todoheap.databinding.ActivityScheduledEventBinding
import edu.rosehulman.todoheap.scheduledevent.controller.ScheduledEventController
import edu.rosehulman.todoheap.scheduledevent.input.ScheduledEventInputModel

class ScheduledEventActivity: AppCompatActivity() {
    lateinit var binding: ActivityScheduledEventBinding
    lateinit var controller: ScheduledEventController
    lateinit var model: ScheduledEventInputModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scheduled_event)
        controller = ScheduledEventController(this)
        binding.controller = controller
        initToolbar()
        initModel()

        binding.inputEventName.requestFocus()
    }

    private fun initToolbar() {
        intent?.getStringExtra(Constants.KEY_SET_TITLE)?.let(binding.toolbar::setTitle)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initModel(){
        model = ScheduledEventInputModel()
        model.setStartAndEndToNextHour()
        if(intent!=null){
            val event = intent.getParcelableExtra<ScheduledEvent>(KEY_SCHEDULED_EVENT)
            event?.let(model::copyFromEvent)
        }

        binding.model = model
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

        val id = intent.getStringExtra(Constants.KEY_SCHEDULED_EVENT_ID)

        val returnIntent = Intent().putExtra(Constants.KEY_SCHEDULED_EVENT, newEvent)
        if(id!=null) returnIntent.putExtra(Constants.KEY_SCHEDULED_EVENT_ID,id)

        setResult(Constants.RESULT_GOOD, returnIntent)
        finish()

    }
}