package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vault_batch_rules",
    foreignKeys = [
        ForeignKey(
            entity = VaultRuleEntity::class,
            parentColumns = ["vr_id"],
            childColumns = ["vr_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["vr_id"], unique = true)]
)
data class VaultBatchRuleEntity(

    @PrimaryKey
    @ColumnInfo(name = "vr_id")
    val vaultRuleId: Int,

    @ColumnInfo(name = "vr_batch_size")
    val batchSize: Int,

    @ColumnInfo(name = "vr_is_recurring")
    val isRecurring: String
)