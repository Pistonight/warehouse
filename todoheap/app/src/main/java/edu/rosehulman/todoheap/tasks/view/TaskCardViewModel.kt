package edu.rosehulman.todoheap.tasks.view

data class TaskCardViewModel(
    val taskName: String,
    val oneSittingConflict: Boolean,
    val showDueWarning: Boolean,
    val pastDue: Boolean,
    val workload: Double,
    val location: String?,
    val dueYear: Int?,
    val dueMonth: Int?,
    val dueDay: Int?,
    val dueHour: Int?,
    val dueMinute: Int?,
    val dueDayOfWeekIndex: Int?,
    val showDue: Boolean,
    val showDueYear: Boolean,
    val showDueDayOfWeekAndTime: Boolean, ){
    val pm get() = dueHour!=null && dueHour>=12
    val hourIn12 get() = if (dueHour==null) 0 else (if (dueHour==0 || dueHour==12) 12 else (dueHour % 12))
        val workloadInt get() = workload.toInt()
}