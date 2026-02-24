package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "vault_recurring_rules",
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
data class VaultRecurringRuleEntity(

    @PrimaryKey
    @ColumnInfo(name = "vr_id")
    val vaultRuleId: Int,

    @ColumnInfo(name = "vr_recurrence_type")
    val recurrenceType: String,

    @ColumnInfo(name = "vr_recurring_day")
    val recurringDay: Int?,

    @ColumnInfo(name = "vr_repeat_interval")
    val repeatInterval: Int?,

    @ColumnInfo(name = "vr_start_date")
    val startDate: Long?,

    @ColumnInfo(name = "vr_end_date")
    val endDate: Long?
)