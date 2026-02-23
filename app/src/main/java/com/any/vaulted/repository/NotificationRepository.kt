package com.any.vaulted.repository

import com.any.vaulted.data.local.model.NotificationData
import com.any.vaulted.data.local.model.QuietWindow
import com.any.vaulted.data.local.model.QuietWindowWithApps
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun saveNotification(notificationData: NotificationData, quietWindowId: Int?)
    fun getAllNotifications(): Flow<List<NotificationData>>
    suspend fun getAllNotificationsOnce(): List<NotificationData>
    suspend fun getNotificationsForQuietWindow(quietWindowId: Int): List<NotificationData>


    suspend fun deleteNotification(id: Int)

    suspend fun saveQuietWindow(quietWindow: QuietWindow): Long
    suspend fun saveQuietWindowApps(quietWindowId: Int, packageNames: List<String>)
    fun getAllQuietWindows(): Flow<List<QuietWindow>>
    fun getQuietWindow(id: Int): Flow<QuietWindow?>
    suspend fun getQuietWindowsWithApps(): List<QuietWindowWithApps>
    suspend fun getNotificationCountForQuietWindow(quietWindowId: Int): Int
    suspend fun clearNotificationsForQuietWindow(quietWindowId: Int)
    suspend fun setQuietWindowEnabled(id: Int, enabled: Boolean)
    suspend fun deleteQuietWindow(id: Int)
}
