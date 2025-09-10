package com.bksd.qrcraftapp.feature.qr.presentation.history.scanned_tab

import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi

sealed interface ScannedTabAction {
    data class OnShareClick(val id: Int) : ScannedTabAction
    data class OnDeleteClick(val id: Int) : ScannedTabAction
    data class OnItemClick(val model: QRUi) : ScannedTabAction
    data class OnItemLongPress(val id: Int) : ScannedTabAction
    data object OnDismissBottomSheet : ScannedTabAction
}