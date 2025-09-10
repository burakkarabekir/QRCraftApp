package com.bksd.qrcraftapp.feature.qr.presentation.history

import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi

sealed interface HistoryAction {
    data object Start : HistoryAction
    data class OnNavigateScanResult(val model: QRUi) : HistoryAction
}