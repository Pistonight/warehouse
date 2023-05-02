package edu.rosehulman.todoheap.account.model

import android.util.Log
import com.google.firebase.firestore.ListenerRegistration
import edu.rosehulman.todoheap.Constants
import edu.rosehulman.todoheap.data.Database

class SettingsModel {
    var showNotifications = false
        set(value) {
            if(field!=value){
                field = value
                updateNotificationSetting()
            }
        }

    var freeNotificationTime: Int = 0
        set(value) {
            if(field!=value){
                field = value
                updateNotificationSetting()
            }
        }

    var dueTimeWeight = 0
        set(value) {
            if(field!=value){
                field = value
                updateWeightSettings()
            }
        }

    var priorityWeight = 0
        set(value) {
        if(field!=value){
            field = value
            updateWeightSettings()
        }
    }

    var procrastinationWeight = 0
        set(value) {
        if(field!=value){
            field = value
            updateWeightSettings()
        }
    }

    var enjoyabilityWeight = 0
        set(value) {
        if(field!=value){
            field = value
            updateWeightSettings()
        }
    }

    var workloadWeight = 0
        set(value) {
            if(field!=value){
                field = value
                updateWeightSettings()
            }
        }


    private fun updateNotificationSetting(){
        Database.notificationSettingsDocument?.set(NotificationSettingModel(showNotifications, freeNotificationTime))
    }

    private fun updateWeightSettings() {
        Database.weightSettingsDocument?.set(WeightSettingModel(
                dueTimeWeight, priorityWeight,procrastinationWeight,enjoyabilityWeight,workloadWeight
        ))
    }

    private var listenerRegistration: ListenerRegistration?=null

    fun init(){
        initDBListeners()

    }

    private fun initSliderValues(model: WeightSettingModel) {
        dueTimeWeight = model.dueTimeWeight
        priorityWeight = model.priorityWeight
        procrastinationWeight = model.procrastinationWeight
        enjoyabilityWeight = model.enjoyabilityWeight
        workloadWeight = model.workloadWeight
    }

    private fun initNotifications(model: NotificationSettingModel){
        showNotifications = model.enable
        freeNotificationTime = model.freeNotificationTime
    }


    private fun initDBListeners(){
        listenerRegistration?.remove()
        listenerRegistration = Database.notificationSettingsDocument?.addSnapshotListener { value, error ->
            if(error!=null) {
                Log.e(Constants.TAG, "Error in SettingsModel: $error")
                return@addSnapshotListener
            }
            val snapshot = value ?: return@addSnapshotListener
            val model = snapshot.toObject(NotificationSettingModel::class.java)?:NotificationSettingModel()
            initNotifications(model)
        }
        Database.weightSettingsDocument?.get()?.addOnSuccessListener { value ->
            val snapshot = value ?: return@addOnSuccessListener
            val weightObject = snapshot.toObject(WeightSettingModel::class.java) ?: WeightSettingModel()
            initSliderValues(weightObject)

        }
    }

}