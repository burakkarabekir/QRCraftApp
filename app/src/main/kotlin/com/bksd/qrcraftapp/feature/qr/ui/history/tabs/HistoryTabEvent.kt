package com.bksd.qrcraftapp.feature.qr.ui.history.tabs

import android.graphics.Bitmap
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

sealed interface HistoryTabEvent {
    data class OnNavigateScanResult(val model: QRUi) : HistoryTabEvent
    data class Share(val qr: Bitmap) : HistoryTabEvent
}