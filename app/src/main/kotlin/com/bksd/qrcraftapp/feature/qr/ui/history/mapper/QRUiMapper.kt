package com.bksd.qrcraftapp.feature.qr.presentation.history.mapper

import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi

fun QR.toQRUi() = QRUi(
    type = type.toUi(),
    title = title,
    rawValue = rawValue,
    displayValue = displayValue,
    timestamp = timestamp,
    format = format,
    qrSource = qrSource,
    id = qrId ?: 0,
)