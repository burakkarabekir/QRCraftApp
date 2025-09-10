package com.bksd.qrcraftapp.feature.qr.ui.camera.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType

data class QRTypeUi(
    val type: QRType,
    @get:StringRes val textRes: Int,
    val icon: Int,
    val containerColor: Color,
    val contentColor: Color,
)