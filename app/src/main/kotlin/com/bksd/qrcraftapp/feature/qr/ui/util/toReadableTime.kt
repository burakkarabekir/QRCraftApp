package com.bksd.qrcraftapp.feature.qr.ui.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toReadableTime(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
    return this.atZone(ZoneId.systemDefault()).format(formatter)
}