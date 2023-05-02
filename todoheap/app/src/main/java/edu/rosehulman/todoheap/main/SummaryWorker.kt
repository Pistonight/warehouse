package edu.rosehulman.todoheap.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.R
import edu.rosehulman.todoheap.account.model.NotificationSettingModel
import edu.rosehulman.todoheap.common.model.FreeEvent
import edu.rosehulman.todoheap.data.Database

class SummaryWorker(
    val context: Context, workerParams: WorkerParameters
): Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("WORK DEBUG","worker")

        //get summary
        Log.d(Constants.TAG, "start getting settings from db")
        Database.notificationSettingsDocument?.get()?.addOnSuccessListener { it ->
            val enabled = it.toObject(NotificationSettingModel::class.java)?.enable?:false
            Log.d(Constants.TAG, "got db $enabled")
            if(!enabled) return@addOnSuccessListener

            Database.freeEventsCollection?.get()?.addOnSuccessListener {
                val freeEventsSnapshot = it ?: return@addOnSuccessListener
                val count = freeEventsSnapshot.documents.size
                val builder = NotificationCompat.Builder(
                    context,
                    Constants.CHANNEL_ID
                )
                    .setSmallIcon(R.drawable.ic_account)//TODO app icon
                    .setContentTitle(context.getString(R.string.notification_summary_title))
                    .setContentText(context.getString(R.string.format_notification_summary_count,count))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)

                val name = context.getString(R.string.channel_name)
                val descriptionText = context.getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                    context. getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
                with(NotificationManagerCompat.from(context)) {
                    notify((System.currentTimeMillis()%1000).toInt(), builder.build())
                }
            }


        }
        return Result.success()
    }
}