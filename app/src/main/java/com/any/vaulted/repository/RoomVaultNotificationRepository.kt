package com.any.vaulted.repository

import android.util.Log
import com.any.vaulted.data.local.dao.VaultHistoryDao
import com.any.vaulted.data.local.dao.VaultNotificationDao
import com.any.vaulted.data.local.dao.VaultRuleDao
import com.any.vaulted.data.local.entity.VaultHistoryEntity
import com.any.vaulted.data.local.entity.VaultNotificationEntity
import com.any.vaulted.data.local.model.NotificationData
import com.any.vaulted.data.local.model.VaultNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomVaultNotificationRepository(
    private val vaultNotificationDao: VaultNotificationDao,
    private val vaultRuleDao: VaultRuleDao,
    private val vaultHistoryDao: VaultHistoryDao
) : VaultNotificationRepository {

    override suspend fun saveNotification(notificationData: NotificationData, vaultId: Int?) {
        if (vaultId == null) return
        try {
            val historyId = resolveHistoryId(vaultId)
            val entity = VaultNotificationEntity(
                notificationId = notificationData.notificationId,
                title = notificationData.title,
                text = notificationData.text,
                postTime = notificationData.postTime,
                tag = notificationData.tag,
                vaultHistoryId = historyId,
                vaultId = vaultId,
                vaultAppPackageName = notificationData.packageName
            )
            vaultNotificationDao.insert(entity)
            Log.d("RoomVaultNotificationRepo", "Notification saved: ${notificationData.title}")
        } catch (e: Exception) {
            Log.e("RoomVaultNotificationRepo", "Error saving notification", e)
            throw e
        }
    }

    override fun getAllNotifications(): Flow<List<NotificationData>> {
        return vaultNotificationDao.getAllNotifications().map { entities ->
            entities.map { entity ->
                NotificationData(
                    id = entity.id,
                    notificationId = entity.notificationId,
                    packageName = entity.vaultAppPackageName,
                    title = entity.title,
                    text = entity.text,
                    postTime = entity.postTime,
                    tag = entity.tag
                )
            }
        }
    }

    override suspend fun getAllNotificationsOnce(): List<NotificationData> {
        return vaultNotificationDao.getAllNotificationsOnce().map { entity ->
            NotificationData(
                id = entity.id,
                notificationId = entity.notificationId,
                packageName = entity.vaultAppPackageName,
                title = entity.title,
                text = entity.text,
                postTime = entity.postTime,
                tag = entity.tag
            )
        }
    }

    override suspend fun getNotificationsForVault(vaultId: Int): List<NotificationData> {
        return vaultNotificationDao.getNotificationsForVault(vaultId).map { entity ->
            NotificationData(
                id = entity.id,
                notificationId = entity.notificationId,
                packageName = entity.vaultAppPackageName,
                title = entity.title,
                text = entity.text,
                postTime = entity.postTime,
                tag = entity.tag
            )
        }
    }

    override suspend fun deleteNotification(id: Int) {
        vaultNotificationDao.deleteNotification(id)
    }

    override suspend fun getNotificationCountForVault(vaultId: Int): Int {
        return vaultNotificationDao.getNotificationCountForVault(vaultId)
    }

    override suspend fun clearNotificationsForVault(vaultId: Int) {
        vaultNotificationDao.deleteNotificationsForVault(vaultId)
    }

    override suspend fun saveVaultNotification(notification: VaultNotification): Long {
        return vaultNotificationDao.insert(
            VaultNotificationEntity(
                id = notification.id,
                notificationId = notification.notificationId,
                title = notification.title,
                text = notification.text,
                postTime = notification.postTime,
                tag = notification.tag,
                vaultHistoryId = notification.vaultHistoryId,
                vaultId = notification.vaultId,
                vaultAppPackageName = notification.vaultAppPackageName
            )
        )
    }

    override suspend fun getVaultNotificationById(id: Int): VaultNotification? {
        return vaultNotificationDao.getNotificationById(id)?.let { entity ->
            VaultNotification(
                id = entity.id,
                notificationId = entity.notificationId,
                title = entity.title,
                text = entity.text,
                postTime = entity.postTime,
                tag = entity.tag,
                vaultHistoryId = entity.vaultHistoryId,
                vaultId = entity.vaultId,
                vaultAppPackageName = entity.vaultAppPackageName
            )
        }
    }

    override fun getAllVaultNotifications(): Flow<List<VaultNotification>> {
        return vaultNotificationDao.getAllNotifications().map { entities ->
            entities.map { entity ->
                VaultNotification(
                    id = entity.id,
                    notificationId = entity.notificationId,
                    title = entity.title,
                    text = entity.text,
                    postTime = entity.postTime,
                    tag = entity.tag,
                    vaultHistoryId = entity.vaultHistoryId,
                    vaultId = entity.vaultId,
                    vaultAppPackageName = entity.vaultAppPackageName
                )
            }
        }
    }

    override suspend fun getAllVaultNotificationsOnce(): List<VaultNotification> {
        return vaultNotificationDao.getAllNotificationsOnce().map { entity ->
            VaultNotification(
                id = entity.id,
                notificationId = entity.notificationId,
                title = entity.title,
                text = entity.text,
                postTime = entity.postTime,
                tag = entity.tag,
                vaultHistoryId = entity.vaultHistoryId,
                vaultId = entity.vaultId,
                vaultAppPackageName = entity.vaultAppPackageName
            )
        }
    }

    override suspend fun getVaultNotificationsForVault(vaultId: Int): List<VaultNotification> {
        return vaultNotificationDao.getNotificationsForVault(vaultId).map { entity ->
            VaultNotification(
                id = entity.id,
                notificationId = entity.notificationId,
                title = entity.title,
                text = entity.text,
                postTime = entity.postTime,
                tag = entity.tag,
                vaultHistoryId = entity.vaultHistoryId,
                vaultId = entity.vaultId,
                vaultAppPackageName = entity.vaultAppPackageName
            )
        }
    }

    private suspend fun resolveHistoryId(vaultId: Int): Int {
        val latest = vaultHistoryDao.getLatestHistoryForVault(vaultId)
        if (latest != null) return latest.id

        val now = System.currentTimeMillis()
        val batchSize = vaultRuleDao.getBatchSizeForVault(vaultId) ?: 0
        return vaultHistoryDao.insertVaultHistory(
            VaultHistoryEntity(
                startTime = now,
                endTime = now,
                batchSize = batchSize,
                vaultId = vaultId
            )
        ).toInt()
    }
}
