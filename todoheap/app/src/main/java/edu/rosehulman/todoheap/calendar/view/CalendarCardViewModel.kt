package edu.rosehulman.todoheap.calendar.view

data class CalendarCardViewModel(
        val showTime: Boolean,
        val hour: Int,
        val minute: Int,
        val title: String,
        val endHour: Int,
        val endMinute: Int,
        val location: String,
) {
    val pm get() = hour>=12
    val hourIn12 get() = if (hour==0 || hour==12) 12 else (hour % 12)
        val endPm get() = endHour>=12
    val endHourIn12 get() = if (endHour==0 || endHour==12) 12 else (endHour % 12)
}