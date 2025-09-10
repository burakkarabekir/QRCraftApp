package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.form

import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi

sealed interface CreateQrFormEvent {
    data object OnNavigateBack : CreateQrFormEvent
    data class OnNavigateToPreview(
        val qrBitmap: String,
        val rawValue: String,
        val displayValue: String,
        val qrType: QRTypeUi,
        val id: Long? = 0L,
    ) : CreateQrFormEvent
}