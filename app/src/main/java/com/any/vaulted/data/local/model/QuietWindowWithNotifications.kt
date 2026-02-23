package com.any.quietly.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.any.quietly.data.local.entity.NotificationEntity
import com.any.quietly.data.local.entity.QuietWindowEntity

data class QuietWindowWithNotifications(
    @Embedded
    val quietWindow: QuietWindowEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "quiet_window_id"
    )
    val notifications: List<NotificationEntity>
)