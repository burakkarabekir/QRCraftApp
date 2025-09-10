package com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

sealed interface ScannedTabAction {
    data class OnShareClick(val id: Long) : ScannedTabAction
    data class OnDeleteClick(val id: Long) : ScannedTabAction
    data class OnItemClick(val model: QRUi) : ScannedTabAction
    data class OnItemLongPress(val id: Long) : ScannedTabAction
    data object OnDismissBottomSheet : ScannedTabAction
}