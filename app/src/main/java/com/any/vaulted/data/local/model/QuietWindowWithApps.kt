package com.any.quietly.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.any.quietly.data.local.entity.QuietWindowAppEntity
import com.any.quietly.data.local.entity.QuietWindowEntity

data class QuietWindowWithApps(
    @Embedded
    val quietWindow: QuietWindowEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "quiet_window_id"
    )
    val apps: List<QuietWindowAppEntity>
)