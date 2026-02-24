package com.any.vaulted.repository

import com.any.vaulted.data.local.dao.VaultRuleDao
import com.any.vaulted.data.local.entity.VaultAppRuleEntity
import com.any.vaulted.data.local.entity.VaultBatchRuleEntity
import com.any.vaulted.data.local.entity.VaultRecurringRuleEntity
import com.any.vaulted.data.local.entity.VaultRuleEntity
import com.any.vaulted.data.local.entity.VaultTimeRuleEntity
import com.any.vaulted.data.local.model.VaultAppRule
import com.any.vaulted.data.local.model.VaultBatchRule
import com.any.vaulted.data.local.model.VaultRecurringRule
import com.any.vaulted.data.local.model.VaultRule
import com.any.vaulted.data.local.model.VaultTimeRule

class RoomVaultRuleRepository(
    private val vaultRuleDao: VaultRuleDao
) : VaultRuleRepository {

    override suspend fun saveVaultRule(rule: VaultRule): Long {
        return vaultRuleDao.insertVaultRule(
            VaultRuleEntity(id = rule.id, accessType = rule.accessType, vaultId = rule.vaultId)
        )
    }

    override suspend fun getVaultRuleForVault(vaultId: Int): VaultRule? {
        return vaultRuleDao.getRuleForVault(vaultId)?.let { entity ->
            VaultRule(id = entity.id, accessType = entity.accessType, vaultId = entity.vaultId)
        }
    }

    override suspend fun saveVaultBatchRule(rule: VaultBatchRule) {
        vaultRuleDao.insertBatchRule(
            VaultBatchRuleEntity(
                vaultRuleId = rule.vaultRuleId,
                batchSize = rule.batchSize,
                isRecurring = rule.isRecurring
            )
        )
    }

    override suspend fun getVaultBatchRule(vaultRuleId: Int): VaultBatchRule? {
        return vaultRuleDao.getBatchRule(vaultRuleId)?.let { entity ->
            VaultBatchRule(
                vaultRuleId = entity.vaultRuleId,
                batchSize = entity.batchSize,
                isRecurring = entity.isRecurring
            )
        }
    }

    override suspend fun saveVaultTimeRule(rule: VaultTimeRule) {
        vaultRuleDao.insertTimeRule(
            VaultTimeRuleEntity(
                vaultRuleId = rule.vaultRuleId,
                accessTime = rule.accessTime,
                accessDate = rule.accessDate,
                isRecurring = rule.isRecurring
            )
        )
    }

    override suspend fun getVaultTimeRule(vaultRuleId: Int): VaultTimeRule? {
        return vaultRuleDao.getTimeRule(vaultRuleId)?.let { entity ->
            VaultTimeRule(
                vaultRuleId = entity.vaultRuleId,
                accessTime = entity.accessTime,
                accessDate = entity.accessDate,
                isRecurring = entity.isRecurring
            )
        }
    }

    override suspend fun saveVaultRecurringRule(rule: VaultRecurringRule) {
        vaultRuleDao.insertRecurringRule(
            VaultRecurringRuleEntity(
                vaultRuleId = rule.vaultRuleId,
                recurrenceType = rule.recurrenceType,
                recurringDay = rule.recurringDay,
                repeatInterval = rule.repeatInterval,
                startDate = rule.startDate,
                endDate = rule.endDate
            )
        )
    }

    override suspend fun getVaultRecurringRule(vaultRuleId: Int): VaultRecurringRule? {
        return vaultRuleDao.getRecurringRule(vaultRuleId)?.let { entity ->
            VaultRecurringRule(
                vaultRuleId = entity.vaultRuleId,
                recurrenceType = entity.recurrenceType,
                recurringDay = entity.recurringDay,
                repeatInterval = entity.repeatInterval,
                startDate = entity.startDate,
                endDate = entity.endDate
            )
        }
    }

    override suspend fun saveVaultAppRules(rules: List<VaultAppRule>) {
        if (rules.isEmpty()) return
        vaultRuleDao.insertAppRules(
            rules.map { rule ->
                VaultAppRuleEntity(
                    id = rule.id,
                    text = rule.text,
                    type = rule.type,
                    matchType = rule.matchType,
                    isCaseSensitive = rule.isCaseSensitive,
                    vaultId = rule.vaultId,
                    packageName = rule.packageName
                )
            }
        )
    }

    override suspend fun getVaultAppRules(vaultId: Int): List<VaultAppRule> {
        return vaultRuleDao.getAppRulesForVault(vaultId).map { entity ->
            VaultAppRule(
                id = entity.id,
                text = entity.text,
                type = entity.type,
                matchType = entity.matchType,
                isCaseSensitive = entity.isCaseSensitive,
                vaultId = entity.vaultId,
                packageName = entity.packageName
            )
        }
    }

    override suspend fun deleteVaultAppRules(vaultId: Int) {
        vaultRuleDao.deleteAppRulesForVault(vaultId)
    }
}
