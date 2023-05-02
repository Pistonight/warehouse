package edu.rosehulman.todoheap.account.model

data class NotificationSettingModel(
    var enable:Boolean = false,
    var freeNotificationTime: Int = 0,
) {
}