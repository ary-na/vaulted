package com.any.vaulted

import android.app.Application
import com.any.vaulted.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VaultedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VaultedApp)
            modules(appModule)
        }
    }
}
