package com.any.vaulted.di

import androidx.room.Room
import com.any.vaulted.data.AppDatabase
import com.any.vaulted.domain.DatabaseNotificationLogger
import com.any.vaulted.domain.GeminiNotificationSummarizer
import com.any.vaulted.domain.NotificationLogger
import com.any.vaulted.repository.NotificationRepository
import com.any.vaulted.repository.RoomNotificationRepository
import com.any.vaulted.ui.viewmodel.NotificationViewModel
import com.any.vaulted.ui.viewmodel.QuietWindowViewModel
import com.any.vaulted.util.NotificationHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "vaulted-db"
        )
            .build()
    }
    single { get<AppDatabase>().notificationDao() }
    single { get<AppDatabase>().quietWindowDao() }
    single<NotificationRepository> { RoomNotificationRepository(get(), get()) }
    single { GeminiNotificationSummarizer() }
    single { NotificationHelper(androidContext()) }
    single<NotificationLogger> { DatabaseNotificationLogger(get()) }

    viewModel { NotificationViewModel(get()) }
    viewModel { QuietWindowViewModel(get()) }
}
