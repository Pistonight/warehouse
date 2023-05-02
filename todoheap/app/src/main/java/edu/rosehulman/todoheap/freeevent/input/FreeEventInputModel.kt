package edu.rosehulman.todoheap.freeevent.input

import com.google.firebase.Timestamp
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.common.model.FreeEvent
import edu.rosehulman.todoheap.data.TimestampUtil
import java.lang.Double
import java.util.*

data class FreeEventInputModel(

    var eventName: String = "",
    var location: String = "",
    var year: Int = 1970,
    var month: Int = 0,
    var dayOfMonth: Int = 1,
    var hour: Int = 0,
    var minute: Int = 0,
    var noDeadline: Boolean = false,
    var workLoad: String = "",
    var oneSitting: Boolean = false,
    var priority: Int = 1,
    var procrastination: Int = 1,
    var enjoyability: Int = 1
) {


    val deadlineTimestamp get () = TimestampUtil.toTimestamp(year,month,dayOfMonth,hour,minute,0)
    val pm get() = hour>=12
    val hourIn12 get() = if (hour==0 || hour==12) 12 else (hour % 12)

    fun setDeadlineToCurrentTime(){
        TimestampUtil.decomposeFields(Timestamp.now()){ year, month, day, hour, minute,_ ->
            this.year = year
            this.month = month
            this.dayOfMonth = day
            this.hour = hour
            this.minute = minute
        }
    }

    fun copyFromEvent(event: FreeEvent){
        eventName = event.name
        location = event.location
        val deadline = event.deadline
        if(deadline!=null){
            TimestampUtil.decomposeFields(deadline){ year, month, day, hour, minute,_ ->
                this.year = year
                this.month = month
                this.dayOfMonth = day
                this.hour = hour
                this.minute = minute
            }
        }

        noDeadline = event.deadline==null
        workLoad = event.workload.toString()
        oneSitting = event.isOneSitting
        priority = event.priority
        procrastination = event.procrastination
        enjoyability = event.enjoyability
    }

    fun toEvent() = FreeEvent(
        name = eventName,
        isOneSitting = oneSitting,
        priority = priority,
        procrastination = procrastination,
        enjoyability = enjoyability,
        workload = Double.parseDouble(workLoad),
        location = location,
        deadline = if (noDeadline) null else deadlineTimestamp
    )

    fun validateInput(): Int?{
        if(eventName=="") return R.string.error_event_name_empty
        if(workLoad=="") return R.string.error_work_load_empty
        return null
    }
}