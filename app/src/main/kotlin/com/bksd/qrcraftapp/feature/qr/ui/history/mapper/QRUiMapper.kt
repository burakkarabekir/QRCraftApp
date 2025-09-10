package com.bksd.qrcraftapp.feature.qr.ui.history.mapper

import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

fun QR.toQRUi() = QRUi(
    type = type.toUi(),
    title = title,
    rawValue = rawValue,
    displayValue = displayValue,
    timestamp = timestamp,
    format = format,
    qrSource = qrSource,
    id = id ?: 0L,
)

fun QRUi.toQR() = QR(
    id = id,
    type = QRType.entries.find { it == type.type } ?: QRType.TEXT,
    rawValue = rawValue,
    displayValue = displayValue,
    timestamp = timestamp,
    qrSource = qrSource,
    title = title.orEmpty(),
    format = format
)