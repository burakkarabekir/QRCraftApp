package com.bksd.campusechojournal.core.database.di

import androidx.room.Room
import com.bksd.campusechojournal.core.database.EchoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<EchoDatabase> {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = EchoDatabase::class.java,
            name = "echos.db"
        ).build()
    }

    single { get<EchoDatabase>().echoDao }
}