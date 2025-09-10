package com.bksd.qrcraftapp.feature.qr.ui.history

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

sealed interface HistoryAction {
    data object Start : HistoryAction
    data class OnNavigateScanResult(val model: QRUi) : HistoryAction
}