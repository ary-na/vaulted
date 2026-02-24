package com.any.vaulted.repository

import com.any.vaulted.data.local.model.Vault
import com.any.vaulted.data.local.model.VaultApp
import com.any.vaulted.data.local.model.VaultWithApps
import com.any.vaulted.data.local.model.VaultWithNotifications
import kotlinx.coroutines.flow.Flow

interface VaultManagementRepository {
    suspend fun saveVault(vault: Vault): Long
    suspend fun saveVaultApps(vaultId: Int, packageNames: List<String>)
    suspend fun getVaultApps(vaultId: Int): List<VaultApp>
    fun getAllVaults(): Flow<List<Vault>>
    fun getVault(id: Int): Flow<Vault?>
    suspend fun getVaultsWithApps(): List<VaultWithApps>
    suspend fun getVaultsWithNotifications(): List<VaultWithNotifications>
    suspend fun setVaultEnabled(id: Int, enabled: Boolean)
    suspend fun deleteVault(id: Int)
}
