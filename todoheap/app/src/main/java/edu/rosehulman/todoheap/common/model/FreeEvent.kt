package edu.rosehulman.todoheap.common.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.QueryDocumentSnapshot
import edu.rosehulman.todoheap.data.TimestampUtil
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class FreeEvent(var name: String = "",
                    var isOneSitting: Boolean = false,
                    var priority: Int = 0,
                    var procrastination: Int = 0,
                    var enjoyability: Int = 0,
                     var workload: Double = 0.0,
                    var location: String="",
                    var deadline: Timestamp? = null) : Parcelable
{
    @IgnoredOnParcel
    @get: Exclude
    var id: String? = null

    companion object {
        @JvmStatic
        fun fromSnapshot(snapshot: QueryDocumentSnapshot): FreeEvent {
            return snapshot.toObject(FreeEvent::class.java).also { it.id = snapshot.id }
        }
    }

    fun isDoable(freeHours: Double):Boolean = !isOneSitting || hasEnoughRoom(freeHours)

    fun hasEnoughRoom(freeHours: Double) = workload<freeHours

    fun isPastDue(now: Timestamp):Boolean = deadline!=null && TimestampUtil.compare(deadline!!, now)<0

    fun getHoursLeft(now: Timestamp):Double =if(deadline==null) 0.0 else ( deadline!!.seconds - now.seconds)/3600.0

    fun value(now: Timestamp, dueTimeWeight: Double, priorityWeight: Double, enjoyabilityWeight: Double, procrastinationWeight: Double, workloadWeight: Double): Double{
        val shiftedPriority = priority-1
        val shiftedProcrastination = procrastination - 1
        val shiftedEnjoyability = enjoyability - 1
        return dueTimeWeight*getHoursLeft(now) + shiftedPriority*priorityWeight + shiftedEnjoyability*enjoyabilityWeight + shiftedProcrastination*procrastinationWeight + workloadWeight*workload
    }
}