package com.bksd.qrcraftapp.feature.qr.presentation.camera.model

data class DetectedQRUi(
    val rawValue: String?,
    val type: Int,
    val format: Int,
    val displayValue: String?,
)