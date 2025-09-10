package com.bksd.qrcraftapp.feature.qr.presentation.main.mapper

import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.DetectedQRUi
import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi
import com.bksd.qrcraftapp.feature.qr.presentation.model.ScanResultScreenType
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultUi

fun DetectedQRUi.toScanResultUi() = ScanResultUi(
    screenType = ScanResultScreenType.SCAN_RESULT,
    qrUi = QRUi(
        type = QRType.entries.find { it.type == type }?.toUi() ?: QRType.TEXT.toUi(),
        title = "",
        rawValue = rawValue.orEmpty(),
        displayValue = displayValue.orEmpty(),
        format = format,
        timestamp = null,
        qrSource = QRSource.SCANNED,
    ),
)

fun ScanResultUi.toQR() = QR(
    type = qrUi.type.type,
    title = qrUi.title.orEmpty(),
    rawValue = qrUi.rawValue,
    displayValue = qrUi.displayValue,
    timestamp = qrUi.timestamp ?: java.time.Instant.now(),
    qrSource = QRSource.SCANNED,
    format = qrUi.format,
    qrId = qrUi.id
)