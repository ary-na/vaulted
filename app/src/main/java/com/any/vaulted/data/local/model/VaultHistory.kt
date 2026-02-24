package com.any.vaulted.data.local.model

data class VaultHistory(
    val id: Int = 0,
    val startTime: Long,
    val endTime: Long,
    val batchSize: Int,
    val vaultId: Int
)
