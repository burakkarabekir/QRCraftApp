package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model

import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi
import com.bksd.qrcraftapp.feature.qr.presentation.model.ScanResultScreenType

data class ScanResultUi(
    val screenType: ScanResultScreenType = ScanResultScreenType.SCAN_RESULT,
    val qrUi: QRUi,
)