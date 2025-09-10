package com.bksd.qrcraftapp.core.database.di

import androidx.room.Room
import com.bksd.qrcraftapp.core.database.QRDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<QRDatabase> {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = QRDatabase::class.java,
            name = "qr-craft.db"
        )
            .build()
    }

    single { get<QRDatabase>().qrDao }
}