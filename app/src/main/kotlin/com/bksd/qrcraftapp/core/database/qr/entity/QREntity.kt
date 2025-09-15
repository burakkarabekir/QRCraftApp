package com.bksd.qrcraftapp.core.database.qr.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource

@Entity(tableName = "qr_table")
data class QREntity(
    @PrimaryKey(autoGenerate = true)
    val qrId: Long = 0L,
    val type: String,
    val title: String,
    val format: Int,
    val rawValue: String,
    val displayValue: String,
    val timestamp: Long,
    val qrSource: QRSource,
)
