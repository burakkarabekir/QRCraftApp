package com.bksd.qrcraftapp.core.ui.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = AppColors.OnPrimary,
    primaryContainer = AppColors.Link,
    onPrimaryContainer = AppColors.OnOverlay,
    inversePrimary = AppColors.OnSurfaceAlt,

    secondary = AppColors.Text,
    secondaryContainer = AppColors.LinkBg,

    background = AppColors.Surface,
    surface = AppColors.SurfaceHigher,
    surfaceVariant = AppColors.Surface,

    onSurface = AppColors.OnSurface,
    onSurfaceVariant = AppColors.OnSurfaceAlt,

    outline = AppColors.OnSurfaceLight,
    outlineVariant = AppColors.OnSurfaceAlt.copy(alpha = 0.5f),

    errorContainer = AppColors.Error,
    onErrorContainer = AppColors.OnError,
    onError = AppColors.Error
)

@Composable
fun QRCraftAppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}