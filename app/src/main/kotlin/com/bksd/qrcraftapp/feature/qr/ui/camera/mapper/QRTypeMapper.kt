package com.bksd.qrcraftapp.feature.qr.ui.camera.mapper

import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.theme.AppColors
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi

fun QRType.toUi(): QRTypeUi {
    return when (this) {
        QRType.TEXT -> QRTypeUi(
            type = this,
            textRes = R.string.text,
            icon = R.drawable.ic_text,
            containerColor = AppColors.TextBg,
            contentColor = AppColors.Text
        )

        QRType.LINK -> QRTypeUi(
            type = this,
            textRes = R.string.link,
            icon = R.drawable.ic_link,
            containerColor = AppColors.LinkBg,
            contentColor = AppColors.Link
        )

        QRType.CONTACT -> QRTypeUi(
            type = this,
            textRes = R.string.contact,
            icon = R.drawable.ic_contact,
            containerColor = AppColors.ContactBg,
            contentColor = AppColors.Contact
        )

        QRType.PHONE -> QRTypeUi(
            type = this,
            textRes = R.string.phone,
            icon = R.drawable.ic_phone,
            containerColor = AppColors.PhoneBg,
            contentColor = AppColors.Phone
        )

        QRType.GEO -> QRTypeUi(
            type = this,
            textRes = R.string.geo,
            icon = R.drawable.ic_geo,
            containerColor = AppColors.GeoBg,
            contentColor = AppColors.Geo
        )

        QRType.WIFI -> QRTypeUi(
            type = this,
            textRes = R.string.wifi,
            icon = R.drawable.ic_wifi,
            containerColor = AppColors.WifiBg,
            contentColor = AppColors.Wifi
        )
    }
}