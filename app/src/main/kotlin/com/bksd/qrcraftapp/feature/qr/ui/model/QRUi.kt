package com.bksd.qrcraftapp.feature.qr.ui.model

import com.bksd.qrcraftapp.core.ui.util.toBase64
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi
import com.bksd.qrcraftapp.feature.qr.ui.util.QRCodeGenerator.encodeBitmap
import com.bksd.qrcraftapp.feature.qr.ui.util.toReadableTime
import com.bksd.qrcraftapp.feature.qr.ui.util.toZxingFormat
import java.time.Instant
import java.util.UUID

data class QRUi(
    val id: Long = 0L,
    val type: QRTypeUi,
    val title: String? = null,
    val rawValue: String,
    val displayValue: String,
    val format: Int = 0,
    val timestamp: Instant? = null,
    val qrSource: QRSource,
) {
    val formattedTime: String = timestamp?.toReadableTime().orEmpty()
    fun getQRBitmap() = encodeBitmap(
        rawValue,
        toZxingFormat(format),
    )

    fun generatedQRPayload(): String = getQRBitmap()?.toBase64().orEmpty()
}
