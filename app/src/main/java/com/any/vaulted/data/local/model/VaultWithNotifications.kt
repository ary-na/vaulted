package com.any.vaulted.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.any.vaulted.data.local.entity.VaultEntity
import com.any.vaulted.data.local.entity.VaultNotificationEntity

data class VaultWithNotifications(
    @Embedded
    val vault: VaultEntity,
    @Relation(
        parentColumn = "vault_id",
        entityColumn = "vault_id"
    )
    val notifications: List<VaultNotificationEntity>
)
