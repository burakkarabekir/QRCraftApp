package com.bksd.qrcraftapp.feature.qr.data.qr.mapper

import com.bksd.qrcraftapp.core.database.qr.entity.QREntity
import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import java.time.Instant

fun QREntity.toQR(): QR = QR(
    type = QRType.valueOf(type),
    rawValue = rawValue,
    displayValue = displayValue,
    timestamp = timestamp?.let { Instant.ofEpochMilli(it) },
    qrSource = qrSource,
    title = title,
    format = format,
    id = qrId
)

fun QR.toEntity(): QREntity = QREntity(
    type = type.name,
    rawValue = rawValue,
    displayValue = displayValue,
    timestamp = timestamp?.toEpochMilli(),
    qrSource = qrSource,
    title = title,
    format = format,
    qrId = id
)