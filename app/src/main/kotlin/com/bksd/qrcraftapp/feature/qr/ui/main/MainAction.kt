package com.bksd.qrcraftapp.feature.qr.ui.main

import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultUi

sealed interface MainAction {
    data object Start : MainAction
    data object OnPermissionDialogPositiveClick : MainAction
    data object OnPermissionDialogNegativeClick : MainAction
    data class OnCameraPermissionResult(val isGranted: Boolean) : MainAction
    data class HasPermission(val isGranted: Boolean) : MainAction
    data class OnScanProcessing(val resultUi: ScanResultUi) : MainAction
}