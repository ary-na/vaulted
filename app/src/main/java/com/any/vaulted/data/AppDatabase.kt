package com.any.vaulted.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.any.vaulted.data.local.dao.VaultDao
import com.any.vaulted.data.local.dao.VaultHistoryDao
import com.any.vaulted.data.local.dao.VaultNotificationDao
import com.any.vaulted.data.local.dao.VaultRuleDao
import com.any.vaulted.data.local.entity.VaultAppEntity
import com.any.vaulted.data.local.entity.VaultAppRuleEntity
import com.any.vaulted.data.local.entity.VaultBatchRuleEntity
import com.any.vaulted.data.local.entity.VaultEntity
import com.any.vaulted.data.local.entity.VaultHistoryEntity
import com.any.vaulted.data.local.entity.VaultNotificationEntity
import com.any.vaulted.data.local.entity.VaultRecurringRuleEntity
import com.any.vaulted.data.local.entity.VaultRuleEntity
import com.any.vaulted.data.local.entity.VaultTimeRuleEntity

@Database(
    entities = [
        VaultEntity::class,
        VaultAppEntity::class,
        VaultRuleEntity::class,
        VaultBatchRuleEntity::class,
        VaultTimeRuleEntity::class,
        VaultRecurringRuleEntity::class,
        VaultHistoryEntity::class,
        VaultNotificationEntity::class,
        VaultAppRuleEntity::class
    ],
    version = 9,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vaultDao(): VaultDao
    abstract fun vaultNotificationDao(): VaultNotificationDao
    abstract fun vaultRuleDao(): VaultRuleDao
    abstract fun vaultHistoryDao(): VaultHistoryDao
}
