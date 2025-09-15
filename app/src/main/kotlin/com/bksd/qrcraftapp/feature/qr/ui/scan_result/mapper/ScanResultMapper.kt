package com.bksd.qrcraftapp.feature.qr.ui.scan_result.mapper

import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.ScanResultUiModel
import java.time.Instant

fun ScanResultUiModel.toQR(): QR = QR(
    id = id,
    type = QRType.entries.find { it.type == type.type } ?: QRType.TEXT,
    rawValue = scannedValue,
    displayValue = displayValue,
    timestamp = Instant.now(),
    qrSource = qrSource,
    title = editedText.orEmpty(),
    format = format,
)