package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form

import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi

sealed interface CreateQrFormEvent {
    data object OnNavigateBack : CreateQrFormEvent
    data class ShowError(val message: String) : CreateQrFormEvent
    data class OnNavigateToPreview(
        val qrBitmap: String,
        val rawValue: String,
        val displayValue: String,
        val qrType: QRTypeUi,
        val id: Long? = 0L,
    ) : CreateQrFormEvent
}