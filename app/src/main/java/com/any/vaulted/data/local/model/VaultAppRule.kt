package com.any.vaulted.data.local.model

data class VaultAppRule(
    val id: Int = 0,
    val text: String,
    val type: String,
    val matchType: String,
    val isCaseSensitive: Boolean,
    val vaultId: Int,
    val packageName: String
)
