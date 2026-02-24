package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "vault_apps",
    primaryKeys = ["vault_id", "va_package_name"],
    foreignKeys = [
        ForeignKey(
            entity = VaultEntity::class,
            parentColumns = ["vault_id"],
            childColumns = ["vault_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class VaultAppEntity(
    @ColumnInfo(name = "vault_id", index = true)
    val vaultId: Int,
    @ColumnInfo(name = "va_package_name", index = true)
    val packageName: String
)