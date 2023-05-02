package edu.rosehulman.todoheap.data

import com.google.firebase.Timestamp
import java.util.*

object TimestampUtil {
    @JvmStatic
    fun toTimestamp(calendar: Calendar) = Timestamp(calendar.time)
    @JvmStatic
    fun toTimestamp(year: Int, month: Int, day: Int) = TimestampUtil.toTimestamp(year,month,day,0,0,0)
    @JvmStatic
    fun toTimestamp(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) =  Timestamp(Calendar.Builder().setDate(year,month,day).setTimeOfDay(hour, minute, second).build().time)
    @JvmStatic
    inline fun <T> decomposeFields(timestamp: Timestamp, callback:(year: Int, month: Int, day:Int, hour: Int, minute: Int, second:Int)->T):T {
        val calendar = toCalendar(timestamp)
        return callback(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        )
    }
    @JvmStatic
    fun getYear(timestamp: Timestamp) = toCalendar(timestamp).get(Calendar.YEAR)
    @JvmStatic
    fun getMonth(timestamp: Timestamp) = toCalendar(timestamp).get(Calendar.MONTH)
    @JvmStatic
    fun getDay(timestamp: Timestamp) = toCalendar(timestamp).get(Calendar.DAY_OF_MONTH)
    @JvmStatic
    fun getHour(timestamp: Timestamp) = toCalendar(timestamp).get(Calendar.HOUR_OF_DAY)
    @JvmStatic
    fun getMinute(timestamp: Timestamp) = toCalendar(timestamp).get(Calendar.MINUTE)
    @JvmStatic
    fun toCalendar(timestamp: Timestamp) = Calendar.Builder().setInstant(timestamp.seconds*1000).build()

    @JvmStatic
    fun nextWholeHour(timestamp: Timestamp): Timestamp {
        var r: Timestamp? = null
        decomposeFields(timestamp){ year, month, day, hour, _, _ ->
            r = toTimestamp(year,month,day,hour+1,0,0)
        }
        return r!!
    }

    @JvmStatic
    fun getDayOfWeek(timestamp: Timestamp) = toCalendar(timestamp).get(Calendar.DAY_OF_WEEK)
    @JvmStatic
    fun calendarDayOfWeekToIndex(dayOfWeek: Int) = if (dayOfWeek==Calendar.SUNDAY) 6 else dayOfWeek - 2

    @JvmStatic
    fun compare(t1: Timestamp, t2: Timestamp) = t1.seconds - t2.seconds

    @JvmStatic
    fun convertToSameTimeOfDay(toConvert: Timestamp, targetDay: Timestamp): Timestamp{
        decomposeFields(targetDay) { year, month, day, _, _, _ ->
            decomposeFields(toConvert) { _,_,_,hour,minute,second ->
                return toTimestamp(year,month,day,hour,minute,second)
            }
        }
    }
}