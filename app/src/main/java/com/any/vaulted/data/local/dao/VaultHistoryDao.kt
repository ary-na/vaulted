package com.any.vaulted.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.any.vaulted.data.local.entity.VaultHistoryEntity

@Dao
interface VaultHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVaultHistory(history: VaultHistoryEntity): Long

    @Query("SELECT * FROM vault_histories WHERE vh_id = :historyId LIMIT 1")
    suspend fun getHistoryById(historyId: Int): VaultHistoryEntity?

    @Query(
        """
        SELECT *
        FROM vault_histories
        WHERE vault_id = :vaultId
        ORDER BY vh_id DESC
        LIMIT 1
        """
    )
    suspend fun getLatestHistoryForVault(vaultId: Int): VaultHistoryEntity?
}
