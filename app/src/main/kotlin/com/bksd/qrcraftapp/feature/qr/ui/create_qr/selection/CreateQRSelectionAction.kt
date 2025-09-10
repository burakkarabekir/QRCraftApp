package com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection

import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi

sealed interface CreateQRSelectionAction {
    data class OnClickType(val type: QRTypeUi) : CreateQRSelectionAction
}