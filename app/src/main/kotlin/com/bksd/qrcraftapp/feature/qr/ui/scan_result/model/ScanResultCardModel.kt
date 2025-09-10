package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model

import android.graphics.Bitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi

data class ScanResultCardState(
    val resultType: QRTypeUi,
    val title: String,
    val resultDescription: UiText,
    val isEditing: Boolean = false,
    val image: Bitmap? = null,
)

data class ScanResultCardActions(
    val onTitleValueChange: (String) -> Unit = {},
    val onShareClick: () -> Unit = {},
    val onCopyClick: () -> Unit = {},
    val readyToSave: (String) -> Unit = {},
)

data class ScanResultCardStyle(
    val tileOverlap: Dp = 100.dp,
    val tileSize: Dp = 200.dp,
    val tileCorner: Dp = 16.dp,
)