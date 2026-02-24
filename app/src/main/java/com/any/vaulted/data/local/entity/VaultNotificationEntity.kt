package com.any.vaulted.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vault_notifications",
    indices = [
        Index(value = ["vh_id", "vault_id"]),
        Index(value = ["vault_id", "va_package_name"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = VaultHistoryEntity::class,
            parentColumns = ["vh_id", "vault_id"],
            childColumns = ["vh_id", "vault_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = VaultAppEntity::class,
            parentColumns = ["vault_id", "va_package_name"],
            childColumns = ["vault_id", "va_package_name"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class VaultNotificationEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vn_id")
    val id: Int = 0,

    @ColumnInfo(name = "vn_notification_id")
    val notificationId: Int,
    @ColumnInfo(name = "vn_title")
    val title: String?,
    @ColumnInfo(name = "vn_text")
    val text: String?,
    @ColumnInfo(name = "vn_post_time")
    val postTime: Long,
    @ColumnInfo(name = "vn_tag")
    val tag: String? = null,

    @ColumnInfo(name = "vh_id")
    val vaultHistoryId: Int,

    @ColumnInfo(name = "vault_id")
    val vaultId: Int,

    @ColumnInfo(name = "va_package_name")
    val vaultAppPackageName: String,
)
