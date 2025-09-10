package com.bksd.qrcraftapp.feature.qr.ui.main

import androidx.compose.runtime.Immutable

@Immutable
data class MainState(
    val hasPermission: Boolean = false,
    val permissionGranted: Boolean = false,
    val permissionDenied: Boolean = false,
    val showCameraPermissionDialog: Boolean = false,
    val isLoading: Boolean = false,
    val isCameraActive: Boolean = false,
    val isScanning: Boolean = false,
) {
    val readyToScan: Boolean get() = permissionGranted && isCameraActive
}