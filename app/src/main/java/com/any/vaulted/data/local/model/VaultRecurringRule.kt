package com.any.vaulted.data.local.model

data class VaultRecurringRule(
    val vaultRuleId: Int,
    val recurrenceType: String,
    val recurringDay: Int?,
    val repeatInterval: Int?,
    val startDate: Long?,
    val endDate: Long?
)
