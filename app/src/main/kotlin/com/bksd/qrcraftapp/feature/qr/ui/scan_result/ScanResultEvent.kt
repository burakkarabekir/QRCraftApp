package com.bksd.qrcraftapp.feature.qr.ui.scan_result

import android.graphics.Bitmap

sealed interface ScanResultEvent {
    data object OnNavigateBack : ScanResultEvent
    data class Share(val qr: Bitmap) : ScanResultEvent
    data class Copy(val content: String) : ScanResultEvent
    data class Save(val editedTitle: String) : ScanResultEvent
}