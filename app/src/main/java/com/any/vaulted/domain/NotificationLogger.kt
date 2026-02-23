package com.any.vaulted.domain

import com.any.vaulted.data.local.model.NotificationData

interface NotificationLogger {
    suspend fun logNotification(notificationData: NotificationData)
    suspend fun getLoggedNotifications(): List<NotificationData>
}
