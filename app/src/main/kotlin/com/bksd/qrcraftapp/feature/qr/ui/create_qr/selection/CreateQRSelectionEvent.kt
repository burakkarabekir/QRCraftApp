package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.selection

import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi

sealed interface CreateQRSelectionEvent {
    data class NavigateToCreateQR(val type: QRTypeUi) : CreateQRSelectionEvent
}