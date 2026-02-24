package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vault_rules",
    foreignKeys = [
        ForeignKey(
            entity = VaultEntity::class,
            parentColumns = ["vault_id"],
            childColumns = ["vault_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["vault_id"], unique = true)]
)
data class VaultRuleEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vr_id")
    val id: Int = 0,

    @ColumnInfo(name = "vr_access_type")
    val accessType: String,

    @ColumnInfo(name = "vault_id")
    val vaultId: Int,
)