package com.any.vaulted.domain

import com.any.vaulted.data.local.model.NotificationData

interface NotificationFilter {
    fun shouldBlock(notificationData: NotificationData): Boolean
}
