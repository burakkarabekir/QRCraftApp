package com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection

import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi

sealed interface CreateQRSelectionEvent {
    data class NavigateToCreateQR(val type: QRTypeUi) : CreateQRSelectionEvent
}