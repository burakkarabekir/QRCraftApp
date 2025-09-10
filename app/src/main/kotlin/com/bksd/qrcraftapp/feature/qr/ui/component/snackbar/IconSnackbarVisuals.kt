package com.bksd.qrcraftapp.feature.qr.ui.component.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

data class IconSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    @field:DrawableRes val iconRes: Int
) : SnackbarVisuals