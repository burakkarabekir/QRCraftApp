package com.bksd.qrcraftapp.feature.qr.presentation.history

import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi

data class HistoryState(
    val qrList: List<QRUi> = emptyList(),
    val isLoading: Boolean = false,
)