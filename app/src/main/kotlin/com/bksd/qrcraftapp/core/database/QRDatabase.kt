package com.bksd.qrcraftapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bksd.qrcraftapp.core.database.qr.converter.QRSourceConverters
import com.bksd.qrcraftapp.core.database.qr.dao.QRDao
import com.bksd.qrcraftapp.core.database.qr.entity.QREntity

@Database(
    entities = [QREntity::class],
    version = 1,
    exportSchema = true,
)

@TypeConverters(
    QRSourceConverters::class,
)
abstract class QRDatabase : RoomDatabase() {
    abstract val qrDao: QRDao
}