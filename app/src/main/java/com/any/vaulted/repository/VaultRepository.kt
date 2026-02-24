package com.any.vaulted.repository

interface VaultRepository :
    VaultNotificationRepository,
    VaultManagementRepository,
    VaultRuleRepository,
    VaultHistoryRepository
