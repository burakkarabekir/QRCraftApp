package com.bksd.qrcraftapp.feature.qr.ui.scan_result

sealed interface ScanResultAction {
    data object OnBackClick : ScanResultAction
    data object OnShareClick : ScanResultAction
    data object OnCopyClick : ScanResultAction

    sealed interface Title : ScanResultAction {
        data class Changed(val text: String) : Title
        data class Saved(val text: String) : Title
    }
}