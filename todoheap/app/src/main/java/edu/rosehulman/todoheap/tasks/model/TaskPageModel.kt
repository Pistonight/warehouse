package edu.rosehulman.todoheap.tasks.model

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.account.model.WeightSettingModel
import edu.rosehulman.todoheap.data.Database
import edu.rosehulman.todoheap.common.model.FreeEvent
import edu.rosehulman.todoheap.common.model.ScheduledEvent
import edu.rosehulman.todoheap.common.view.RecyclerViewModelProvider
import edu.rosehulman.todoheap.data.TimestampUtil
import edu.rosehulman.todoheap.tasks.view.TaskCardViewModel
import edu.rosehulman.todoheap.tasks.view.recycler.TaskCardAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class TaskPageModel: RecyclerViewModelProvider<TaskCardViewModel> {
    var recyclerAdapter: TaskCardAdapter? = null
    private val taskList = ArrayList<FreeEvent>()
    @Volatile
    private var freeEventsListenerRegistration: ListenerRegistration?=null

    private var freeHours = 0.0
    private var dueTimeWeight = 0.0
    private var priorityWeight = 0.0
    private var procrastinationWeight = 0.0
    private var enjoyabilityWeight = 0.0
    private var workloadWeight = 0.0

    fun init(){
        freeEventsListenerRegistration?.remove()
        taskList.clear()
        recyclerAdapter?.notifyDataSetChanged()
        reloadParameters {
            initDBListener()
        }
    }
    @Synchronized
    private  fun  initDBListener(){
        freeEventsListenerRegistration?.remove()
        freeEventsListenerRegistration = Database.freeEventsCollection?.addSnapshotListener { value, error ->
            if(error!=null) {
                Log.e(Constants.TAG, "Error in TaskPageModel: $error")
                return@addSnapshotListener
            }
            val snapshot = value ?: return@addSnapshotListener
            for(docChange in snapshot.documentChanges) {
                val event = FreeEvent.fromSnapshot(docChange.document)
                when(docChange.type){
                    DocumentChange.Type.ADDED -> {
                        val index = insertEventIntoSortedList(event)
                        recyclerAdapter?.notifyItemInserted(index)
                    }
                    DocumentChange.Type.REMOVED -> {
                        taskList.indexOfFirst { it.id == event.id }.let {
                            taskList.removeAt(it)
                            recyclerAdapter?.notifyItemRemoved(it)
                        }
                    }
                    DocumentChange.Type.MODIFIED -> {
                        taskList.indexOfFirst { it.id == event.id }.let {
                            taskList.removeAt(it)
                            val newIndex = insertEventIntoSortedList(event)
                            if(newIndex!=it){
                                recyclerAdapter?.notifyItemRemoved(it)
                                recyclerAdapter?.notifyItemInserted(newIndex)
                            }else{
                                recyclerAdapter?.notifyItemChanged(it)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getViewModel(position: Int): TaskCardViewModel {
        val event = taskList[position]
        val now = Timestamp.now()

        val deadline = event.deadline
        val pastDue = deadline?.let{ TimestampUtil.compare(it, now)<0 } ?:false
        val deadlineCalendar = deadline?.let(TimestampUtil::toCalendar)
        val dueYear = deadlineCalendar?.get(Calendar.YEAR)
        val dueDayOfWeek = deadlineCalendar?.get(Calendar.DAY_OF_WEEK)?.let(TimestampUtil::calendarDayOfWeekToIndex)
        Log.d(Constants.TAG, "event ${event.name}, hasroom=${event.hasEnoughRoom(freeHours)}, freeHours = $freeHours")
        return TaskCardViewModel(
                taskName = event.name,
                oneSittingConflict = event.isOneSitting && !event.hasEnoughRoom(freeHours),
                showDueWarning = pastDue || (deadline!=null && deadline.seconds - now.seconds < 3600*24),
                pastDue = pastDue,
                workload = event.workload,
                location = event.location,
                dueYear = dueYear,
                dueMonth = deadlineCalendar?.get(Calendar.MONTH),
                dueDay = deadlineCalendar?.get(Calendar.DAY_OF_MONTH),
                dueHour = deadlineCalendar?.get(Calendar.HOUR_OF_DAY),
                dueMinute = deadlineCalendar?.get(Calendar.MINUTE),
                showDue = deadline!=null,
                showDueYear = dueYear != null && dueYear!=TimestampUtil.getYear(now),
                dueDayOfWeekIndex = dueDayOfWeek,
                showDueDayOfWeekAndTime = deadline!=null && abs(deadline.seconds - now.seconds)<3600*24*7,//less then 7 days
        )
    }

    operator fun get(position: Int) = taskList[position]

    override val size: Int
        get() = taskList.size

    private fun compareEvents(e1: FreeEvent, e2: FreeEvent): Int {
        val isE1Doable = e1.isDoable(freeHours)
        val isE2Doable = e2.isDoable(freeHours)
        val now = Timestamp.now()
        if(isE1Doable && isE2Doable){
            //both are doable, check based on due, priority, procastination & enjoyability
            //past dues are always scheduled first
            val isE1PastDue = e1.isPastDue(now)
            val isE2PastDue = e2.isPastDue(now)
            if(isE1PastDue != isE2PastDue){
                //one is past due, schedule past due first
                return if(isE1PastDue) -1 else 1
            }else{
                //both are not due or past due
                // if one has deadline, schedule it first
                val e1HasDeadline = e1.deadline!=null
                val e2HasDeadline = e2.deadline!=null
                if(e1HasDeadline!=e2HasDeadline){
                    return if(e1HasDeadline)-1 else 1
                }
                // both have deadline, and are not due or past due
                // base on time until due priority, procrast and enjoyability

                //priority value lower = more important
                //procrastination value lower = less procrastination (schedule sooner)
                //weights are from 1 to -1, 0 means neutral

                val e1Value = e1.value(now,dueTimeWeight,priorityWeight,enjoyabilityWeight,procrastinationWeight,workloadWeight)
                val e2Value = e2.value(now,dueTimeWeight,priorityWeight,enjoyabilityWeight,procrastinationWeight,workloadWeight)
                return if (e1Value - e2Value<0) -1 else 1
            }
        }else{
            if(isE1Doable){
                return -1
            }else if(isE2Doable){
                return 1
            }
            return 0
        }

    }

    private fun insertEventIntoSortedList(e: FreeEvent): Int=
        taskList.indexOfFirst { compareEvents(it,e)>0 }.let {
            if(it>=0){
                taskList.add(it, e)
                it
            }else{
                taskList.add(e)
                taskList.size-1
            }

        }

    private fun reloadParameters(callback: ()->Unit) {
        val now = Timestamp.now()

        Database.scheduledEventsCollection?.whereGreaterThanOrEqualTo("endTime",now)?.get()?.addOnSuccessListener {
            val value = it ?: return@addOnSuccessListener
            var freeSeconds = Long.MAX_VALUE
            for(document in value.documents){
                val scheduledEvent = ScheduledEvent.fromSnapshot(document) ?: continue
                if(TimestampUtil.compare(scheduledEvent.startTime, now)<0){
                    //start before, check if event time is after now
                    val startTimeOnly = TimestampUtil.convertToSameTimeOfDay(scheduledEvent.startTime, now)
                    if(TimestampUtil.compare(startTimeOnly, now)>=0){
                        //event starts after now, check if it repeats on today
                        val dayOfWeekIndex = TimestampUtil.calendarDayOfWeekToIndex(TimestampUtil.getDayOfWeek(now))
                        if(scheduledEvent.isRepeatingOn(dayOfWeekIndex)){
                            val currentFreeSeconds = startTimeOnly.seconds - now.seconds
                            freeSeconds = freeSeconds.coerceAtMost(currentFreeSeconds)
                        }
                    }
                }else{
                    //starts after now
                    val currentFreeSeconds = scheduledEvent.startTime.seconds - now.seconds
                    freeSeconds = freeSeconds.coerceAtMost(currentFreeSeconds)

                }
            }
            freeHours = freeSeconds / 3600.0
            Log.d(Constants.TAG,"Loaded Free Hours = $freeHours")
            Database.weightSettingsDocument?.get()?.addOnSuccessListener { value ->
                val snapshot = value ?: return@addOnSuccessListener
                val weightObject = snapshot.toObject(WeightSettingModel::class.java) ?: WeightSettingModel()
                val converter = {v: Int->v/100.0}
                dueTimeWeight = converter(weightObject.dueTimeWeight)
                priorityWeight = converter(weightObject.priorityWeight)
                procrastinationWeight = converter(weightObject.procrastinationWeight)
                enjoyabilityWeight = converter(weightObject.enjoyabilityWeight)
                workloadWeight = converter(weightObject.workloadWeight)
                callback()
            }


        }
    }



}