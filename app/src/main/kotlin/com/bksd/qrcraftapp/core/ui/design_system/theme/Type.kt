package com.bksd.qrcraftapp.core.ui.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bksd.qrcraftapp.R

private val SUSE = FontFamily(
    Font(R.font.suse_regular, FontWeight.Normal),
    Font(R.font.suse_medium, FontWeight.Medium),
    Font(R.font.suse_semibold, FontWeight.SemiBold)
)

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = SUSE,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 32.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SUSE,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp,
        lineHeight = 24.sp,
        letterSpacing = (-1).sp
    ),
    labelLarge = TextStyle(
        fontFamily = SUSE,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = (-1).sp
    ),
    labelMedium = TextStyle(
        fontFamily = SUSE,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = (-1).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = SUSE,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = (-1).sp
    ),
    bodySmall = TextStyle(
        fontFamily = SUSE,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = (-1).sp
    )
)