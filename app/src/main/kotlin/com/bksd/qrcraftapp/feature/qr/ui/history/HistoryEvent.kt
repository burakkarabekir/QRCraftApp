package com.bksd.qrcraftapp.feature.qr.ui.history

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

sealed interface HistoryEvent {
    data class OnNavigateScanResult(val model: QRUi) : HistoryEvent
}