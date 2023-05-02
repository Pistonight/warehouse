package edu.rosehulman.todoheap.tasks.controller

class TaskCardController(val controller: TaskController, val position: () -> Int) {

    fun onClick() {
        controller.editAt(position())
    }

}