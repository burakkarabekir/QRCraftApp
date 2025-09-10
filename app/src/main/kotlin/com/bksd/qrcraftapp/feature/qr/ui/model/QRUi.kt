package com.bksd.qrcraftapp.feature.qr.presentation.model

import com.bksd.qrcraftapp.core.presentation.util.toBase64
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi
import com.bksd.qrcraftapp.feature.qr.presentation.util.QRCodeGenerator.encodeBitmap
import com.bksd.qrcraftapp.feature.qr.presentation.util.toReadableTime
import com.bksd.qrcraftapp.feature.qr.presentation.util.toZxingFormat
import java.time.Instant

data class QRUi(
    val id: Long? = null,
    val type: QRTypeUi,
    val title: String,
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
