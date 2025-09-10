package com.bksd.qrcraftapp.feature.qr.ui.scan_result

import android.graphics.Bitmap
import com.bksd.qrcraftapp.core.ui.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType

data class ScanResultState(
    val uiModel: ScanResultUiModel = ScanResultUiModel(),
)

data class ScanResultUiModel(
    val id: Long? = null,
    val screenType: ScanResultScreenType = ScanResultScreenType.SCAN_RESULT,
    val type: QRType = QRType.TEXT,
    val scannedValue: String = UiText.Empty.toString(),
    val displayValue: String = UiText.Empty.toString(),
    val format: Int = 0,
    val barcodeImage: Bitmap? = null,
    val editedText: String? = null,
    val isEditing: Boolean = false,
)