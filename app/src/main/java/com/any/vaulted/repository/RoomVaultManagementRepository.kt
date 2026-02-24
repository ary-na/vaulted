package com.any.vaulted.repository

import com.any.vaulted.data.local.dao.VaultDao
import com.any.vaulted.data.local.dao.VaultRuleDao
import com.any.vaulted.data.local.entity.VaultAppEntity
import com.any.vaulted.data.local.entity.VaultBatchRuleEntity
import com.any.vaulted.data.local.entity.VaultEntity
import com.any.vaulted.data.local.entity.VaultRuleEntity
import com.any.vaulted.data.local.model.Vault
import com.any.vaulted.data.local.model.VaultApp
import com.any.vaulted.data.local.model.VaultWithApps
import com.any.vaulted.data.local.model.VaultWithNotifications
import kotlinx.coroutines.flow.Flow

class RoomVaultManagementRepository(
    private val vaultDao: VaultDao,
    private val vaultRuleDao: VaultRuleDao
) : VaultManagementRepository {

    override suspend fun saveVault(vault: Vault): Long {
        val vaultId = vaultDao.insertVault(
            VaultEntity(name = vault.name, isEnabled = vault.isEnabled)
        )

        val ruleId = vaultRuleDao.insertVaultRule(
            VaultRuleEntity(accessType = "BATCH", vaultId = vaultId.toInt())
        )

        vaultRuleDao.insertBatchRule(
            VaultBatchRuleEntity(
                vaultRuleId = ruleId.toInt(),
                batchSize = vault.batchSize,
                isRecurring = "false"
            )
        )
        return vaultId
    }

    override suspend fun saveVaultApps(vaultId: Int, packageNames: List<String>) {
        if (packageNames.isEmpty()) return
        val entities = packageNames.map { packageName ->
            VaultAppEntity(vaultId = vaultId, packageName = packageName)
        }
        vaultDao.insertVaultApps(entities)
    }

    override suspend fun getVaultApps(vaultId: Int): List<VaultApp> {
        return vaultDao.getAppsForVault(vaultId).map { entity ->
            VaultApp(vaultId = entity.vaultId, packageName = entity.packageName)
        }
    }

    override fun getAllVaults(): Flow<List<Vault>> = vaultDao.getAllVaults()

    override fun getVault(id: Int): Flow<Vault?> = vaultDao.getVault(id)

    override suspend fun getVaultsWithApps(): List<VaultWithApps> = vaultDao.getVaultsWithApps()

    override suspend fun getVaultsWithNotifications(): List<VaultWithNotifications> =
        vaultDao.getVaultsWithNotifications()

    override suspend fun setVaultEnabled(id: Int, enabled: Boolean) {
        vaultDao.setVaultEnabled(id, enabled)
    }

    override suspend fun deleteVault(id: Int) {
        vaultDao.deleteVault(id)
    }
}
