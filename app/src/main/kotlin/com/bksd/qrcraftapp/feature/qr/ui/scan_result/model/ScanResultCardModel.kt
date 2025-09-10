package com.bksd.qrcraftapp.feature.qr.ui.scan_result.model

import android.graphics.Bitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.ui.util.UiText
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi

data class ScanResultCardState(
    val resultType: QRTypeUi,
    val title: String,
    val resultDescription: UiText,
    val isEditing: Boolean = false,
    val image: Bitmap? = null,
    val isEditable: Boolean = true,
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