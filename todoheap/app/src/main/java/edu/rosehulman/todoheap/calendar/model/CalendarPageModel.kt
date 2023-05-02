package edu.rosehulman.todoheap.calendar.model

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.calendar.view.CalendarCardViewModel
import edu.rosehulman.todoheap.data.Database
import edu.rosehulman.todoheap.common.model.FreeEvent
import edu.rosehulman.todoheap.common.model.ScheduledEvent
import edu.rosehulman.todoheap.common.view.RecyclerViewModelProvider
import edu.rosehulman.todoheap.data.TimestampUtil
import edu.rosehulman.todoheap.databinding.FragmentCalendarBinding
import edu.rosehulman.todoheap.tasks.view.recycler.CalendarCardAdapter
import edu.rosehulman.todoheap.tasks.view.TaskCardViewModel
import java.util.*
import kotlin.collections.ArrayList

class CalendarPageModel: RecyclerViewModelProvider<CalendarCardViewModel> {
    var recyclerAdapter: CalendarCardAdapter? = null
    var binding: FragmentCalendarBinding?=null
    private lateinit var selectedDayTimestamp: Timestamp
    val selectedYear  get() = TimestampUtil.getYear(selectedDayTimestamp)
    val selectedMonth  get() = TimestampUtil.getMonth(selectedDayTimestamp)
    val selectedDay  get() = TimestampUtil.getDay(selectedDayTimestamp)
    val selectedDayOfWeek get() = TimestampUtil.calendarDayOfWeekToIndex(TimestampUtil.getDayOfWeek(selectedDayTimestamp))
    private lateinit var nextDayOfSelectedDayTimestamp: Timestamp
    private val eventList = ArrayList<ScheduledEvent>()
    @Volatile
    private var listenerRegistration: ListenerRegistration?=null
    init {
        val now = Calendar.getInstance()
        val year = now.get(Calendar.YEAR)
        val month = now.get(Calendar.MONTH)
        val day = now.get(Calendar.DAY_OF_MONTH)
        selectDay(year,month, day)
    }

    fun selectDay(year: Int, month: Int, day: Int){
        selectedDayTimestamp = TimestampUtil.toTimestamp(year,month, day);//Timestamp(Calendar.Builder().setDate(year,month,day).setTimeOfDay(0, 0, 0).build().time)
        nextDayOfSelectedDayTimestamp = TimestampUtil.toTimestamp(year,month, day+1);
        binding?.model = this
        init()
    }

    fun previousDay(){
        TimestampUtil.decomposeFields(selectedDayTimestamp) { year, month, day, _, _, _ ->
            selectDay(year,month,day-1)
        }
    }

    fun nextDay(){
        TimestampUtil.decomposeFields(selectedDayTimestamp) { year, month, day, _, _, _ ->
            selectDay(year,month,day+1)
        }
    }

    fun init(){
        listenerRegistration?.remove()
        eventList.clear()
        recyclerAdapter?.notifyDataSetChanged()
        initDBListener()
    }
    @Synchronized
    private fun initDBListener(){
        listenerRegistration?.remove()
        listenerRegistration = Database.scheduledEventsCollection
            ?.whereLessThanOrEqualTo("startTime",nextDayOfSelectedDayTimestamp)
            ?.addSnapshotListener { value, error ->
                if(error!=null) {
                    Log.e(Constants.TAG, "Error in CalendarPageModel: $error")
                    return@addSnapshotListener
                }
                val snapshot = value ?: return@addSnapshotListener
                val dayOfWeekIndex = TimestampUtil.calendarDayOfWeekToIndex(TimestampUtil.getDayOfWeek(selectedDayTimestamp))

                for(docChange in snapshot.documentChanges) {
                    val event = ScheduledEvent.fromSnapshot(docChange.document)
                    when(docChange.type){
                        DocumentChange.Type.ADDED -> {
                            //insert if the event is on the selected day, or is repeated and is
                            var shouldAdd = false
                            if(TimestampUtil.compare(event.startTime, selectedDayTimestamp)>=0 && TimestampUtil.compare(event.startTime,nextDayOfSelectedDayTimestamp)<=0){
                                //event is on the selected day
                                shouldAdd = true
                            }else if(TimestampUtil.compare(event.endTime,selectedDayTimestamp) >=0&&event.isRepeatingOn(dayOfWeekIndex)){
                                shouldAdd = true
                            }
                            if(shouldAdd) {
                                val index = insertEventIntoSortedList(event)
                                recyclerAdapter?.notifyItemInserted(index)
                                notifyShowTimeChangeWhenInsertedAt(index)
                            }
                        }
                        DocumentChange.Type.REMOVED -> {
                            eventList.indexOfFirst { it.id == event.id }.let {
                                if(it>=0) {
                                    eventList.removeAt(it)
                                    recyclerAdapter?.notifyItemRemoved(it)
                                    notifyShowTimeChangeWhenRemovedAt(it)
                                }
                            }
                        }
                        DocumentChange.Type.MODIFIED -> {
                            eventList.indexOfFirst { it.id == event.id }.let {
                                if(it>0){
                                    eventList.removeAt(it)
                                    val newIndex = insertEventIntoSortedList(event)
                                    if(newIndex!=it){
                                        recyclerAdapter?.notifyItemRemoved(it)
                                        notifyShowTimeChangeWhenRemovedAt(it)
                                        recyclerAdapter?.notifyItemInserted(newIndex)
                                        notifyShowTimeChangeWhenInsertedAt(newIndex)
                                    }else{
                                        recyclerAdapter?.notifyItemChanged(it)
                                        if(it<eventList.size-1){
                                            recyclerAdapter?.notifyItemChanged(it+1)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }

    override fun getViewModel(position: Int): CalendarCardViewModel {
        val event = eventList[position]
        val hour = TimestampUtil.getHour(event.startTime)
        val minute = TimestampUtil.getMinute(event.startTime)
        var showTime = true
        if(position>0){
            val previousHour = TimestampUtil.getHour(eventList[position-1].startTime)
            if(hour==previousHour) showTime = false
        }
        return CalendarCardViewModel(showTime, hour, minute,eventList[position].name,TimestampUtil.getHour(event.endTime),TimestampUtil.getMinute(event.endTime),event.location)
    }

    private fun notifyShowTimeChangeWhenInsertedAt(position: Int){
        if(position<eventList.size-1){
            val hour = TimestampUtil.getHour(eventList[position].startTime)
            val nextHour = TimestampUtil.getHour(eventList[position+1].startTime)
            if(hour==nextHour){
                recyclerAdapter?.notifyItemChanged(position+1)
            }
        }
    }

    private fun notifyShowTimeChangeWhenRemovedAt(position: Int){
        if(position==0){
            recyclerAdapter?.notifyItemChanged(0)
        }else if (position<eventList.size){
            val hour = TimestampUtil.getHour(eventList[position].startTime)
            val prevHour = TimestampUtil.getHour(eventList[position-1].startTime)
            if(hour!=prevHour){
                recyclerAdapter?.notifyItemChanged(position)
            }
        }
    }

    operator fun get(position: Int) = eventList[position]

    override val size: Int
        get() = eventList.size

    private fun compareEvents(e1: ScheduledEvent, e2: ScheduledEvent): Int {
        var startTime1: Int = 0
        TimestampUtil.decomposeFields(e1.startTime){ _,_,_,hour,minute,_ ->
            startTime1 = hour*60 + minute;
        }
        var startTime2: Int = 0
        TimestampUtil.decomposeFields(e2.startTime){ _,_,_,hour,minute,_ ->
            startTime2 = hour*60 + minute;
        }
        return startTime1 - startTime2;
    }

    private fun insertEventIntoSortedList(e: ScheduledEvent): Int=
        eventList.indexOfFirst { compareEvents(it,e)>0 }.let {
            if(it>=0){
                eventList.add(it, e)
                it
            }else{
                eventList.add(e)
                eventList.size-1
            }

        }



}