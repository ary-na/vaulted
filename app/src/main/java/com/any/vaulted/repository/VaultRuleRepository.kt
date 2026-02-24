package com.any.vaulted.repository

import com.any.vaulted.data.local.model.VaultAppRule
import com.any.vaulted.data.local.model.VaultBatchRule
import com.any.vaulted.data.local.model.VaultRecurringRule
import com.any.vaulted.data.local.model.VaultRule
import com.any.vaulted.data.local.model.VaultTimeRule

interface VaultRuleRepository {
    suspend fun saveVaultRule(rule: VaultRule): Long
    suspend fun getVaultRuleForVault(vaultId: Int): VaultRule?
    suspend fun saveVaultBatchRule(rule: VaultBatchRule)
    suspend fun getVaultBatchRule(vaultRuleId: Int): VaultBatchRule?
    suspend fun saveVaultTimeRule(rule: VaultTimeRule)
    suspend fun getVaultTimeRule(vaultRuleId: Int): VaultTimeRule?
    suspend fun saveVaultRecurringRule(rule: VaultRecurringRule)
    suspend fun getVaultRecurringRule(vaultRuleId: Int): VaultRecurringRule?
    suspend fun saveVaultAppRules(rules: List<VaultAppRule>)
    suspend fun getVaultAppRules(vaultId: Int): List<VaultAppRule>
    suspend fun deleteVaultAppRules(vaultId: Int)
}
