package com.bksd.qrcraftapp.app.di

import com.bksd.qrcraftapp.app.QRCraftApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as QRCraftApp).applicationScope
    }
}