package edu.rosehulman.todoheap.scheduledevent.input

import com.google.firebase.Timestamp
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.common.model.FreeEvent
import edu.rosehulman.todoheap.common.model.ScheduledEvent
import edu.rosehulman.todoheap.data.TimestampUtil

data class ScheduledEventInputModel(
    var eventName: String = "",
    var location: String = "",
    var startYear: Int = 1970,
    var startMonth: Int = 0,
    var startDay: Int = 1,
    var startHour: Int = 0,
    var startMinute: Int = 0,
    var endYear: Int = 1970,
    var endMonth: Int = 0,
    var endDay: Int = 1,
    var endHour: Int = 0,
    var endMinute: Int = 0,
    var repeatMonday: Boolean = false,
    var repeatTuesday: Boolean = false,
    var repeatWednesday: Boolean = false,
    var repeatThursday: Boolean = false,
    var repeatFriday: Boolean = false,
    var repeatSaturday: Boolean = false,
    var repeatSunday: Boolean = false,
) {
    val startPm get() = startHour>=12
    val startHourIn12 get() = if (startHour==0 || startHour==12) 12 else (startHour % 12)
    val endPm get() = endHour>=12
    val endHourIn12 get() = if (endHour==0 || endHour==12) 12 else (endHour % 12)

    fun setStartAndEndToNextHour(){
        val start = TimestampUtil.nextWholeHour(Timestamp.now())
        val end = TimestampUtil.nextWholeHour(start)

        TimestampUtil.decomposeFields(start){ year, month, day, hour, _, _ ->
            this.startYear = year
            this.startMonth = month
            this.startDay = day
            this.startHour = hour
            this.startMinute = 0
        }

        TimestampUtil.decomposeFields(end){ year, month, day, hour, _, _ ->
            this.endYear = year
            this.endMonth = month
            this.endDay = day
            this.endHour = hour
            this.endMinute = 0
        }
    }

    fun copyFromEvent(event: ScheduledEvent) {
        eventName = event.name
        location = event.location ?: ""
        val startTime = event.startTime
        if (startTime != null) {
            TimestampUtil.decomposeFields(startTime) { year, month, day, hour, minute, _ ->
                this.startYear = year
                this.startMonth = month
                this.startDay = day
                this.startHour = hour
                this.startMinute = minute
            }
        }
        val endTime = event.endTime
        if(endTime!=null){
            TimestampUtil.decomposeFields(endTime){ year, month, day, hour, minute, _ ->
                this.endYear = year
                this.endMonth = month
                this.endDay = day
                this.endHour = hour
                this.endMinute = minute
            }
        }
        event.repeatOn?.let{
            repeatMonday=it[0]
            repeatTuesday=it[1]
            repeatWednesday=it[2]
            repeatThursday=it[3]
            repeatFriday=it[4]
            repeatSaturday=it[5]
            repeatSunday=it[6]
        }
    }

    fun toEvent(): ScheduledEvent = ScheduledEvent(
            name = eventName,
            location = location,
            startTime = startTimestamp,
            endTime = endTimestamp,
            repeatOn = listOf(repeatMonday, repeatTuesday,repeatWednesday,repeatThursday,repeatFriday,repeatSaturday,repeatSunday),
    )

    val endTimestamp get() = TimestampUtil.toTimestamp(endYear,endMonth,endDay,endHour,endMinute,0)
    val startTimestamp  get()=TimestampUtil.toTimestamp(startYear,startMonth,startDay,startHour,startMinute,0)

    fun validateInput(): Int? {
        if(endTimestamp.seconds < startTimestamp.seconds) return R.string.error_end_before_start
        if(eventName=="") return R.string.error_event_name_empty
        return null
    }
}