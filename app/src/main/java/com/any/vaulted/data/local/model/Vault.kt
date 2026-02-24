package com.any.vaulted.data.local.model

data class Vault(
    val id: Int = 0,
    val name: String,
    val batchSize: Int,
    val isEnabled: Boolean = true
)
