package com.any.vaulted.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.any.vaulted.data.local.entity.VaultNotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultNotificationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notification: VaultNotificationEntity): Long

    @Query("SELECT * FROM vault_notifications WHERE vn_id = :id LIMIT 1")
    suspend fun getNotificationById(id: Int): VaultNotificationEntity?

    @Query("SELECT * FROM vault_notifications ORDER BY vn_post_time DESC")
    fun getAllNotifications(): Flow<List<VaultNotificationEntity>>

    @Query("SELECT * FROM vault_notifications ORDER BY vn_post_time DESC")
    suspend fun getAllNotificationsOnce(): List<VaultNotificationEntity>

    @Query("SELECT * FROM vault_notifications WHERE vault_id = :vaultId ORDER BY vn_post_time DESC")
    suspend fun getNotificationsForVault(vaultId: Int): List<VaultNotificationEntity>

    @Query("SELECT COUNT(*) FROM vault_notifications WHERE vault_id = :vaultId")
    suspend fun getNotificationCountForVault(vaultId: Int): Int

    @Query("DELETE FROM vault_notifications WHERE vn_id = :id")
    suspend fun deleteNotification(id: Int)

    @Query("DELETE FROM vault_notifications WHERE vault_id = :vaultId")
    suspend fun deleteNotificationsForVault(vaultId: Int)
}
