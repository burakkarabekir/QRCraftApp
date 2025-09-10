package com.bksd.qrcraftapp.feature.qr.ui.main

import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultUi

sealed interface MainEvent {
    /** Ask the OS to show the camera permission dialog */
    data object RequestCameraPermission : MainEvent

    /** Check current camera permission (requires Context on the UI side) */
    data object CheckCameraPermission : MainEvent

    /** Show a transient message */
    data class ShowSnack(val message: String) : MainEvent

    /** Finish the app (used when user denies mandatory permission) */
    data object CloseApp : MainEvent
    data class OnDoneScanning(val resultUi: ScanResultUi) : MainEvent
}