package com.any.vaulted.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.any.vaulted.data.local.dao.NotificationDao
import com.any.vaulted.data.local.entity.NotificationEntity
import com.any.vaulted.data.local.entity.QuietWindowAppEntity
import com.any.vaulted.data.local.dao.QuietWindowDao
import com.any.vaulted.data.local.entity.QuietWindowEntity

@Database(
    entities = [NotificationEntity::class, VaultEntity::class, QuietWindowAppEntity::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao
    abstract fun quietWindowDao(): QuietWindowDao
}
