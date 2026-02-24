package com.any.vaulted.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.any.vaulted.data.local.entity.VaultAppRuleEntity
import com.any.vaulted.data.local.entity.VaultBatchRuleEntity
import com.any.vaulted.data.local.entity.VaultRecurringRuleEntity
import com.any.vaulted.data.local.entity.VaultRuleEntity
import com.any.vaulted.data.local.entity.VaultTimeRuleEntity

@Dao
interface VaultRuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVaultRule(rule: VaultRuleEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBatchRule(rule: VaultBatchRuleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeRule(rule: VaultTimeRuleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecurringRule(rule: VaultRecurringRuleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppRules(rules: List<VaultAppRuleEntity>)

    @Query("SELECT * FROM vault_rules WHERE vault_id = :vaultId LIMIT 1")
    suspend fun getRuleForVault(vaultId: Int): VaultRuleEntity?

    @Query(
        """
        SELECT br.vr_batch_size
        FROM batch_rules br
        INNER JOIN vault_rules vr ON vr.vr_id = br.vr_id
        WHERE vr.vault_id = :vaultId
        LIMIT 1
        """
    )
    suspend fun getBatchSizeForVault(vaultId: Int): Int?

    @Query("SELECT * FROM batch_rules WHERE vr_id = :vaultRuleId LIMIT 1")
    suspend fun getBatchRule(vaultRuleId: Int): VaultBatchRuleEntity?

    @Query("SELECT * FROM time_rules WHERE vr_id = :vaultRuleId LIMIT 1")
    suspend fun getTimeRule(vaultRuleId: Int): VaultTimeRuleEntity?

    @Query("SELECT * FROM recurring_rules WHERE vr_id = :vaultRuleId LIMIT 1")
    suspend fun getRecurringRule(vaultRuleId: Int): VaultRecurringRuleEntity?

    @Query("SELECT * FROM vault_app_rules WHERE vault_id = :vaultId")
    suspend fun getAppRulesForVault(vaultId: Int): List<VaultAppRuleEntity>

    @Query("DELETE FROM vault_app_rules WHERE vault_id = :vaultId")
    suspend fun deleteAppRulesForVault(vaultId: Int)
}
