package com.bksd.qrcraftapp.feature.qr.domain.model

import java.time.Instant

data class QR(
    val id: Long = 0L,
    val type: QRType,
    val title: String,
    val rawValue: String,
    val displayValue: String,
    val format: Int,
    val timestamp: Instant? = null,
    val qrSource: QRSource,
)

enum class QRSource { SCANNED, GENERATED }
