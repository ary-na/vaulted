package com.any.vaulted.data.local.model

data class VaultNotification(
    val id: Int = 0,
    val notificationId: Int,
    val title: String?,
    val text: String?,
    val postTime: Long,
    val tag: String? = null,
    val vaultHistoryId: Int,
    val vaultId: Int,
    val vaultAppPackageName: String
)
