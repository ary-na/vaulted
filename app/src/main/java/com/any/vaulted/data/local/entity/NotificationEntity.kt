package com.any.quietly.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.any.quietly.data.local.entity.QuietWindowEntity

@Entity(
    tableName = "notifications",
    foreignKeys = [
        ForeignKey(
            entity = QuietWindowEntity::class,
            parentColumns = ["id"],
            childColumns = ["quiet_window_id"],
            onDelete = ForeignKey.Companion.CASCADE // If a QuietWindow is deleted, its notifications are also deleted
        )
    ]
)
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val notificationId: Int,
    val packageName: String,
    val title: String?,
    val text: String?,
    val postTime: Long,
    val tag: String? = null,
    @ColumnInfo(name = "quiet_window_id", index = true)
    val quietWindowId: Int
)