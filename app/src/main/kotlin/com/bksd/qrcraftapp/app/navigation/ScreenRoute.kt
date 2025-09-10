package com.bksd.qrcraftapp.app.navigation

import androidx.navigation3.runtime.NavKey
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.navigation_bar.NavigationBarItem
import com.bksd.qrcraftapp.core.ui.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType
import kotlinx.serialization.Serializable

@Serializable
data object MainScreenRoute : NavKey, NavigationBarItem {
    override val iconRes: Int
        get() = R.drawable.ic_scan
    override val contentDescription: String
        get() = "Scan Icon"
}

@Serializable
data object CreateQRScreenRoute : NavKey, NavigationBarItem {
    override val iconRes: Int
        get() = R.drawable.ic_create_qr
    override val contentDescription: String
        get() = "Create QR Icon"
}

@Serializable
data object HistoryScreenRoute : NavKey, NavigationBarItem {
    override val iconRes: Int
        get() = R.drawable.ic_history
    override val contentDescription: String
        get() = "History Icon"
}

@Serializable
data class ScanResultScreenRoute(
    val screenType: ScanResultScreenType,
    val type: QRType,
    val title: String,
    val rawValue: String,
    val displayValue: String,
    val format: Int = 0,
    val barcodeImage: String,
    val id: Long? = null,
) : NavKey

@Serializable
data class CreateQrFormScreenRoute(
    val type: QRType,
    val rawValue: String = UiText.Empty.toString(),
    val displayValue: String = UiText.Empty.toString(),
    val barcodeImage: String = UiText.Empty.toString(),
    val format: Int = 0,
    val title: String = UiText.Empty.toString(),
    val id: Long? = null,
) : NavKey
