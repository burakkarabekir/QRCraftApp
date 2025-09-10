package com.bksd.qrcraftapp.feature.qr.presentation.history

import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi

sealed interface HistoryEvent {
    data class OnNavigateScanResult(val model: QRUi) : HistoryEvent
}