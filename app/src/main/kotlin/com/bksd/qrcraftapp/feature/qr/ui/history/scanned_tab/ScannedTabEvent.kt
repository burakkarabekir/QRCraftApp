package com.bksd.qrcraftapp.feature.qr.presentation.history.scanned_tab

import android.graphics.Bitmap
import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi

sealed interface ScannedTabEvent {
    data class OnNavigateScanResult(val model: QRUi) : ScannedTabEvent
    data class Share(val qr: Bitmap) : ScannedTabEvent
}