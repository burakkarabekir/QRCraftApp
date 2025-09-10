package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.mapper

import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultUiModel
import java.time.Instant

fun ScanResultUiModel.toQR(): QR = QR(
    qrId = id,
    type = QRType.entries.find { it.type == type.type } ?: QRType.TEXT,
    rawValue = scannedValue,
    displayValue = displayValue,
    timestamp = Instant.now(),
    qrSource = QRSource.SCANNED,
    title = editedText.orEmpty(),
    format = format
)