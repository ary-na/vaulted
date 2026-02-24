package com.any.vaulted.repository

import com.any.vaulted.data.local.dao.VaultDao
import com.any.vaulted.data.local.dao.VaultHistoryDao
import com.any.vaulted.data.local.dao.VaultNotificationDao
import com.any.vaulted.data.local.dao.VaultRuleDao

class RoomVaultRepository(
    vaultNotificationDao: VaultNotificationDao,
    vaultDao: VaultDao,
    vaultRuleDao: VaultRuleDao,
    vaultHistoryDao: VaultHistoryDao
) : VaultRepository,
    VaultNotificationRepository by RoomVaultNotificationRepository(
        vaultNotificationDao = vaultNotificationDao,
        vaultRuleDao = vaultRuleDao,
        vaultHistoryDao = vaultHistoryDao
    ),
    VaultManagementRepository by RoomVaultManagementRepository(
        vaultDao = vaultDao,
        vaultRuleDao = vaultRuleDao
    ),
    VaultRuleRepository by RoomVaultRuleRepository(
        vaultRuleDao = vaultRuleDao
    ),
    VaultHistoryRepository by RoomVaultHistoryRepository(
        vaultHistoryDao = vaultHistoryDao
    )
