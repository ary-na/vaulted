package com.any.vaulted.repository

import com.any.vaulted.data.local.model.VaultHistory

interface VaultHistoryRepository {
    suspend fun saveVaultHistory(history: VaultHistory): Long
    suspend fun getVaultHistoryById(historyId: Int): VaultHistory?
    suspend fun getLatestVaultHistoryForVault(vaultId: Int): VaultHistory?
}
