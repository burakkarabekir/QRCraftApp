package com.bksd.qrcraftapp.feature.qr.ui.history.tabs.model

import androidx.compose.ui.graphics.Color
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.theme.AppColors

enum class OptionSelectionType(val iconRes: Int, val textRes: Int, val contentColorRes: Color) {
    DELETE(
        iconRes = R.drawable.ic_delete,
        textRes = R.string.delete,
        contentColorRes = AppColors.Error
    ),
    SHARE(
        iconRes = R.drawable.ic_share,
        textRes = R.string.share,
        contentColorRes = AppColors.OnSurface
    ),
}