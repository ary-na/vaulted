package com.any.vaulted.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.any.vaulted.data.local.entity.VaultAppEntity
import com.any.vaulted.data.local.entity.VaultEntity
import com.any.vaulted.data.local.model.Vault
import com.any.vaulted.data.local.model.VaultWithApps
import com.any.vaulted.data.local.model.VaultWithNotifications
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVault(vault: VaultEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVaultApps(apps: List<VaultAppEntity>)

    @Query(
        """
        SELECT
            v.vault_id AS id,
            v.vault_name AS name,
            COALESCE(br.vr_batch_size, 0) AS batchSize,
            v.vault_is_enabled AS isEnabled
        FROM vaults v
        LEFT JOIN vault_rules vr ON vr.vault_id = v.vault_id
        LEFT JOIN batch_rules br ON br.vr_id = vr.vr_id
        ORDER BY v.vault_id DESC
        """
    )
    fun getAllVaults(): Flow<List<Vault>>

    @Query(
        """
        SELECT
            v.vault_id AS id,
            v.vault_name AS name,
            COALESCE(br.vr_batch_size, 0) AS batchSize,
            v.vault_is_enabled AS isEnabled
        FROM vaults v
        LEFT JOIN vault_rules vr ON vr.vault_id = v.vault_id
        LEFT JOIN batch_rules br ON br.vr_id = vr.vr_id
        WHERE v.vault_id = :id
        LIMIT 1
        """
    )
    fun getVault(id: Int): Flow<Vault?>

    @Query("DELETE FROM vaults WHERE vault_id = :id")
    suspend fun deleteVault(id: Int)

    @Query("UPDATE vaults SET vault_is_enabled = :enabled WHERE vault_id = :id")
    suspend fun setVaultEnabled(id: Int, enabled: Boolean)

    @Transaction
    @Query("SELECT * FROM vaults")
    suspend fun getVaultsWithApps(): List<VaultWithApps>

    @Transaction
    @Query("SELECT * FROM vaults")
    suspend fun getVaultsWithNotifications(): List<VaultWithNotifications>

    @Query("SELECT * FROM vault_apps WHERE vault_id = :vaultId")
    suspend fun getAppsForVault(vaultId: Int): List<VaultAppEntity>
}
