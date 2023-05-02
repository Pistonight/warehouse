package edu.rosehulman.todoheap.calendar.controller

class CalendarCardController(private val controller: CalendarController, private val position: ()->Int) {
    fun onClick(){
        controller.editAt(position())
    }
}