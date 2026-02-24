package com.any.vaulted.repository

import com.any.vaulted.data.local.dao.VaultHistoryDao
import com.any.vaulted.data.local.entity.VaultHistoryEntity
import com.any.vaulted.data.local.model.VaultHistory

class RoomVaultHistoryRepository(
    private val vaultHistoryDao: VaultHistoryDao
) : VaultHistoryRepository {

    override suspend fun saveVaultHistory(history: VaultHistory): Long {
        return vaultHistoryDao.insertVaultHistory(
            VaultHistoryEntity(
                id = history.id,
                startTime = history.startTime,
                endTime = history.endTime,
                batchSize = history.batchSize,
                vaultId = history.vaultId
            )
        )
    }

    override suspend fun getVaultHistoryById(historyId: Int): VaultHistory? {
        return vaultHistoryDao.getHistoryById(historyId)?.let { entity ->
            VaultHistory(
                id = entity.id,
                startTime = entity.startTime,
                endTime = entity.endTime,
                batchSize = entity.batchSize,
                vaultId = entity.vaultId
            )
        }
    }

    override suspend fun getLatestVaultHistoryForVault(vaultId: Int): VaultHistory? {
        return vaultHistoryDao.getLatestHistoryForVault(vaultId)?.let { entity ->
            VaultHistory(
                id = entity.id,
                startTime = entity.startTime,
                endTime = entity.endTime,
                batchSize = entity.batchSize,
                vaultId = entity.vaultId
            )
        }
    }
}
