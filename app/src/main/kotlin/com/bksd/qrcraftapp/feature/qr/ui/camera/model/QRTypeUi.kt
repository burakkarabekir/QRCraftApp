package com.bksd.qrcraftapp.feature.qr.presentation.camera.model

import androidx.compose.ui.graphics.Color
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType

data class QRTypeUi(
    val type: QRType,
    val text: UiText,
    val icon: Int,
    val containerColor: Color,
    val contentColor: Color,
)