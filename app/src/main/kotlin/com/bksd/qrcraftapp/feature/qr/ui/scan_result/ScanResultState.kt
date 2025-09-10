package com.bksd.qrcraftapp.feature.qr.presentation.scan_result

import android.graphics.Bitmap
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.model.ScanResultScreenType

data class ScanResultState(
    val uiModel: ScanResultUiModel = ScanResultUiModel(),
)

data class ScanResultUiModel(
    val id: Long? = null,
    val screenType: ScanResultScreenType = ScanResultScreenType.SCAN_RESULT,
    val type: QRType = QRType.TEXT,
    val scannedValue: String = "",
    val displayValue: String = "",
    val format: Int = 0,
    val barcodeImage: Bitmap? = null,
    val editedText: String? = null,
    val isEditing: Boolean = false,
)