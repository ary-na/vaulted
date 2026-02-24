package com.any.vaulted.data.local.model

data class VaultTimeRule(
    val vaultRuleId: Int,
    val accessTime: Long,
    val accessDate: Long,
    val isRecurring: Boolean
)
