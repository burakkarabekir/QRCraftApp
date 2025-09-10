package com.bksd.qrcraftapp.app

import android.app.Application
import com.bksd.qrcraftapp.BuildConfig
import com.bksd.qrcraftapp.app.di.appModule
import com.bksd.qrcraftapp.core.database.di.databaseModule
import com.bksd.qrcraftapp.feature.qr.di.featureQRModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class QRCraftApp : Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@QRCraftApp)
            modules(appModule, featureQRModule, databaseModule)

        }
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}