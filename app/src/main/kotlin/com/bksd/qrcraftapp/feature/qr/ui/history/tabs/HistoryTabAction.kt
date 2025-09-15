package com.bksd.qrcraftapp.feature.qr.ui.history.tabs

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

sealed interface HistoryTabAction {
    data class OnShareClick(val id: Long) : HistoryTabAction
    data class OnDeleteClick(val id: Long) : HistoryTabAction
    data class OnItemClick(val model: QRUi) : HistoryTabAction
    data class OnItemLongPress(val id: Long) : HistoryTabAction
    data object OnDismissBottomSheet : HistoryTabAction
}