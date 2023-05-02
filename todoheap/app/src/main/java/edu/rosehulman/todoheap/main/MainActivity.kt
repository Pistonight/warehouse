package edu.rosehulman.todoheap.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.account.controller.AccountController
import edu.rosehulman.todoheap.account.model.SettingsModel
import edu.rosehulman.todoheap.calendar.model.CalendarPageModel
import edu.rosehulman.todoheap.common.model.FreeEvent
import edu.rosehulman.todoheap.common.model.ScheduledEvent
import edu.rosehulman.todoheap.data.Database
import edu.rosehulman.todoheap.data.TimestampUtil
import edu.rosehulman.todoheap.databinding.ActivityMainBinding
import edu.rosehulman.todoheap.tasks.model.TaskPageModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var app: App
    lateinit var controller: Controller
    lateinit var accountController: AccountController
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initApp()
        initController()
        val navView: BottomNavigationView = binding.bottomNav

        navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_tasks,
                R.id.navigation_calendar,
                R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)


        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id==R.id.navigation_account){
                binding.fab.hide()
            }else{
                binding.fab.show()
            }
            if(destination.id==R.id.navigation_tasks){
                app.taskPageModel.init()
            }
            if(destination.id==R.id.navigation_account){
                app.settingsModel.init()
            }
        }
        if(savedInstanceState==null)
        navController.navigate(R.id.navigation_tasks)
    }

    override fun onStart() {
        super.onStart()
        accountController.setupListener()
        WorkManager.getInstance(this).cancelUniqueWork(Constants.SUMMARY_WORK)
    }

    override fun onStop() {
        super.onStop()
        accountController.removeListener()
        if(app.settingsModel.showNotifications){
            val workRequest =  when(app.settingsModel.freeNotificationTime){
                0 -> {
                    // 15 minutes
                    PeriodicWorkRequestBuilder<SummaryWorker>(15, TimeUnit.MINUTES).setInitialDelay(15, TimeUnit.MINUTES).build()
                }
                1 -> {
                    // 1 hour
                    PeriodicWorkRequestBuilder<SummaryWorker>(1, TimeUnit.HOURS).setInitialDelay(1, TimeUnit.HOURS).build()
                }
                2->{
                    //12 hours
                    PeriodicWorkRequestBuilder<SummaryWorker>(12,  TimeUnit.HOURS).setInitialDelay(12, TimeUnit.HOURS).build()
                }
                3->{
                    //1 day
                    PeriodicWorkRequestBuilder<SummaryWorker>(1, TimeUnit.DAYS).setInitialDelay(1, TimeUnit.DAYS).build()
                }
                else -> null
            }
            if(workRequest!=null){
                WorkManager.getInstance(this).enqueueUniquePeriodicWork(Constants.SUMMARY_WORK, ExistingPeriodicWorkPolicy.KEEP, workRequest)
            }
        }else{
            WorkManager.getInstance(this).cancelUniqueWork(Constants.SUMMARY_WORK)
        }

    }

    private fun initApp() {
        val taskPageModel = TaskPageModel()
        val calendarPageModel = CalendarPageModel()
        val settingsModel = SettingsModel()
        app = App(taskPageModel, calendarPageModel, settingsModel)
    }

    private fun initController() {
        controller = Controller(this)
        binding.controller = controller
        accountController = AccountController(this)
    }

    fun loginOK(){
        navController.navigate(R.id.navigation_tasks)
        app.settingsModel.init()
        app.calendarPageModel.init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            Constants.RC_ADD_FREE_EVENT -> {
                val event = data?.getParcelableExtra<FreeEvent>(Constants.KEY_FREE_EVENT)
                if(event!=null) Database.freeEventsCollection?.add(event)
                navController.navigate(R.id.navigation_tasks)
            }
            Constants.RC_EDIT_FREE_EVENT -> {
                val event = data?.getParcelableExtra<FreeEvent>(Constants.KEY_FREE_EVENT)
                val id = data?.getStringExtra(Constants.KEY_FREE_EVENT_ID)
                event?.id = id
                if(event!=null) Database.freeEventsCollection?.document(id!!)?.set(event)
                navController.navigate(R.id.navigation_tasks)
            }
            Constants.RC_ADD_SCHEDULED_EVENT -> {
                val event = data?.getParcelableExtra<ScheduledEvent>(Constants.KEY_SCHEDULED_EVENT)
                if(event!=null){
                    Database.scheduledEventsCollection?.add(event)
                    TimestampUtil.decomposeFields(event.startTime){ year, month, day, _,_,_->
                        app.calendarPageModel.selectDay(year,month,day)
                    }
                    navController.navigate(R.id.navigation_calendar)
                }
            }
            Constants.RC_EDIT_SCHEDULED_EVENT -> {
                val event = data?.getParcelableExtra<ScheduledEvent>(Constants.KEY_SCHEDULED_EVENT)
                val id = data?.getStringExtra(Constants.KEY_SCHEDULED_EVENT_ID)
                event?.id = id
                if(event!=null){
                    Database.scheduledEventsCollection?.document(id!!)?.set(event)
                    TimestampUtil.decomposeFields(event.startTime){ year, month, day, _,_,_->
                        app.calendarPageModel.selectDay(year,month,day)
                    }
                    navController.navigate(R.id.navigation_calendar)
                }
            }
        }

    }

}