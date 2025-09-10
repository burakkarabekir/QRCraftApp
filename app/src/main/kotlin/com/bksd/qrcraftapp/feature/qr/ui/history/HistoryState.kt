package com.bksd.qrcraftapp.feature.qr.ui.history

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

data class HistoryState(
    val qrList: List<QRUi> = emptyList(),
    val isLoading: Boolean = false,
)