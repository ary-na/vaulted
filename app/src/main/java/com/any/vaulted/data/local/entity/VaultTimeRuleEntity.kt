package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vault_time_rules",
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
data class VaultTimeRuleEntity(

    @PrimaryKey
    @ColumnInfo(name = "vr_id")
    val vaultRuleId: Int,

    @ColumnInfo(name = "vr_access_time")
    val accessTime: Long,

    @ColumnInfo(name = "vr_access_date")
    val accessDate: Long,

    @ColumnInfo(name = "vr_is_recurring")
    val isRecurring: Boolean
)