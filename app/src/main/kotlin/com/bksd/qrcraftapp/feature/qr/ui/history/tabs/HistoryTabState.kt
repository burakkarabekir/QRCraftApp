package com.bksd.qrcraftapp.feature.qr.ui.history.tabs

data class ScannedTabState(
    val uiModel: ScannedTabUiModel = ScannedTabUiModel(),
)

data class ScannedTabUiModel(
    val showOptionSheet: Boolean = false,
    val selectedItemIdForOptions: Long? = null,
)