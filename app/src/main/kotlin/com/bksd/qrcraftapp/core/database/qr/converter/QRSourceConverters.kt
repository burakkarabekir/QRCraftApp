package com.bksd.qrcraftapp.core.database.qr.converter

import androidx.room.TypeConverter
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource

class QRSourceConverters {

    @TypeConverter
    fun fromQR(type: QRSource): String = type.name

    @TypeConverter
    fun toQR(type: String): QRSource = QRSource.valueOf(type)
}