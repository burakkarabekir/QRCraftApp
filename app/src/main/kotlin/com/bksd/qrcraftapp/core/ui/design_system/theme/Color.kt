package com.bksd.qrcraftapp.core.ui.design_system.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {
    val Primary = Color(0xFFEBFF69)
    val OnPrimary = Color(0xFF000000)

    val Surface = Color(0xFFEDF2F5)
    val SurfaceHigher = Color(0xFFFFFFFF)
    val OnSurface = Color(0xFF273037)
    val OnSurfaceAlt = Color(0xFF505F6A)
    val OnSurfaceLight = Color(0xFF8C99A2)

    val Overlay = Color(0x80000000)
    val OnOverlay = Color(0xFFFFFFFF)    

    val Link = Color(0xFF373F05)
    val LinkBg = Color(0x4DEBFF69)

    val Error = Color(0xFFF12244)
    val OnError = Color(0xFFFFFFFF)
    val Success = Color(0xFF4DDA9D)
    val OnSuccess = Color(0xFF000000)

    val Text = Color(0xFF583DC5)
    val TextBg = Color(0x1A583DC5)

    val Contact = Color(0xFF259570)
    val ContactBg = Color(0x1A259570)

    val Geo = Color(0xFF851D5C)
    val GeoBg = Color(0x1A851D5C)

    val Phone = Color(0xFFC63017)
    val PhoneBg = Color(0x1AC63017)

    val Wifi = Color(0xFF1F44CD)
    val WifiBg = Color(0x1A1F44CD)
    val EditableTextPlaceholder = Color(0xffC5CBCF)

    val ColorScheme.success: Color
        get() = Success

    val ColorScheme.editableTextPlaceholder: Color
        get() = EditableTextPlaceholder
}