package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vault_app_rules",
    indices = [Index(value = ["vault_id", "va_package_name"])],
    foreignKeys = [
        ForeignKey(
            entity = VaultAppEntity::class,
            parentColumns = ["vault_id", "va_package_name"],
            childColumns = ["vault_id", "va_package_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class VaultAppRuleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "var_id")
    val id: Int = 0,
    @ColumnInfo(name = "var_text")
    val text: String,
    @ColumnInfo(name = "var_type")
    val type: String,
    @ColumnInfo(name = "var_match_type")
    val matchType: String,
    @ColumnInfo(name = "var_is_case_sensitive")
    val isCaseSensitive: Boolean,
    @ColumnInfo(name = "vault_id")
    val vaultId: Int,
    @ColumnInfo(name = "va_package_name")
    val packageName: String,
)
