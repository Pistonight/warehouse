package edu.rosehulman.todoheap.main

import edu.rosehulman.todoheap.account.model.SettingsModel
import edu.rosehulman.todoheap.calendar.model.CalendarPageModel
import edu.rosehulman.todoheap.tasks.model.TaskPageModel

/**
 * Main model for the app
 */
class App(
    val taskPageModel: TaskPageModel,
    val calendarPageModel: CalendarPageModel,
    val settingsModel: SettingsModel
) {
    fun init(){
        taskPageModel.init()
        calendarPageModel.init()
        settingsModel.init()
    }
}