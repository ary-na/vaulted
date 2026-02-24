package com.any.vaulted.repository

import com.any.vaulted.data.local.model.NotificationData
import com.any.vaulted.data.local.model.VaultNotification
import kotlinx.coroutines.flow.Flow

interface VaultNotificationRepository {
    suspend fun saveNotification(notificationData: NotificationData, vaultId: Int?)
    fun getAllNotifications(): Flow<List<NotificationData>>
    suspend fun getAllNotificationsOnce(): List<NotificationData>
    suspend fun getNotificationsForVault(vaultId: Int): List<NotificationData>
    suspend fun deleteNotification(id: Int)
    suspend fun getNotificationCountForVault(vaultId: Int): Int
    suspend fun clearNotificationsForVault(vaultId: Int)

    suspend fun saveVaultNotification(notification: VaultNotification): Long
    suspend fun getVaultNotificationById(id: Int): VaultNotification?
    fun getAllVaultNotifications(): Flow<List<VaultNotification>>
    suspend fun getAllVaultNotificationsOnce(): List<VaultNotification>
    suspend fun getVaultNotificationsForVault(vaultId: Int): List<VaultNotification>
}
