package com.bksd.qrcraftapp.feature.qr.ui.main.mapper

import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.DetectedQRUi
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultUi

fun DetectedQRUi.toScanResultUi(): ScanResultUi {
    val type = QRType.entries.find { it.type == type }?.toUi() ?: QRType.TEXT.toUi()

    return ScanResultUi(
        screenType = ScanResultScreenType.SCAN_RESULT,
        qrUi = QRUi(
            type = type,
            rawValue = rawValue.orEmpty(),
            displayValue = displayValue.orEmpty(),
            format = format,
            timestamp = null,
            qrSource = QRSource.SCANNED,
        ),
    )
}

fun ScanResultUi.toQR() = QR(
    type = qrUi.type.type,
    title = qrUi.title.orEmpty(),
    rawValue = qrUi.rawValue,
    displayValue = qrUi.displayValue,
    timestamp = qrUi.timestamp,
    qrSource = QRSource.SCANNED,
    format = qrUi.format,
    id = qrUi.id,
)