package com.bksd.qrcraftapp.core.ui.design_system.navigation_bar

import androidx.annotation.DrawableRes

interface NavigationBarItem {
    @get:DrawableRes
    val iconRes: Int
    val contentDescription: String
}