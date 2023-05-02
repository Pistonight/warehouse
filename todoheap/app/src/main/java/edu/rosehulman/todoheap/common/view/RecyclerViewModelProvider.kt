package edu.rosehulman.todoheap.common.view

interface RecyclerViewModelProvider<T> {
    fun getViewModel(position: Int): T

    val size: Int
}