package com.bksd.qrcraftapp.feature.qr.ui.history

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

data class HistoryState(
    val scannedQRList: List<QRUi> = emptyList(),
    val generatedQRList: List<QRUi> = emptyList(),
    val isLoading: Boolean = false,
)