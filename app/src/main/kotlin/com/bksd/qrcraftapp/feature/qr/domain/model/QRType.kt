package com.bksd.qrcraftapp.feature.qr.domain.model

import kotlinx.serialization.Serializable

/**
 * Enum class representing different types of QR codes.
 */

@Serializable
enum class QRType(val type: Int) {
    TEXT(7),
    LINK(8),
    CONTACT(1),
    PHONE(4),
    GEO(10),
    WIFI(9)
}