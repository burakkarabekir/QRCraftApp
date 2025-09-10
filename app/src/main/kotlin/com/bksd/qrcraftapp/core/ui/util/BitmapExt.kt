package com.bksd.qrcraftapp.core.ui.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeByteArray
import android.util.Base64
import timber.log.Timber
import java.io.ByteArrayOutputStream

fun Bitmap.toBase64(): String {
    val outputStream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
}

fun String.toBitmap(): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(this, Base64.DEFAULT)
        decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        Timber.e(e, "Failed to decode Base64 string to Bitmap")
        null
    }
}