package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.selection

import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi

sealed interface CreateQRSelectionAction {
    data class OnClickType(val type: QRTypeUi) : CreateQRSelectionAction
}