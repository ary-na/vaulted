package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vault_histories",
    indices = [Index(value = ["vh_id", "vault_id"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = VaultEntity::class,
            parentColumns = ["vault_id"],
            childColumns = ["vault_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class VaultHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vh_id")
    val id: Int = 0,
    @ColumnInfo(name = "vh_start_time")
    val startTime: Long,
    @ColumnInfo(name = "vh_end_time")
    val endTime: Long,
    @ColumnInfo(name = "vh_batch_size")
    val batchSize: Int,
    @ColumnInfo(name = "vault_id", index = true)
    val vaultId: Int

)
