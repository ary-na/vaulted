package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaults")
data class VaultEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vault_id")
    val id: Int = 0,
    @ColumnInfo(name = "vault_name")
    val name: String,
    @ColumnInfo(name = "vault_is_enabled")
    val isEnabled: Boolean = true
)