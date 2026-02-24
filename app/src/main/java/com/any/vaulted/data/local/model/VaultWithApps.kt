package com.any.vaulted.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.any.vaulted.data.local.entity.VaultAppEntity
import com.any.vaulted.data.local.entity.VaultEntity

data class VaultWithApps(
    @Embedded
    val vault: VaultEntity,
    @Relation(
        parentColumn = "vault_id",
        entityColumn = "vault_id"
    )
    val apps: List<VaultAppEntity>
)
