package com.bksd.qrcraftapp.feature.qr.ui.scan_result.model

import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType

data class ScanResultUi(
    val screenType: ScanResultScreenType,
    val qrUi: QRUi,
)