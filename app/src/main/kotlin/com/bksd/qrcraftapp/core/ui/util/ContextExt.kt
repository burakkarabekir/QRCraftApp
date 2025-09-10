package com.bksd.qrcraftapp.core.ui.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.bksd.qrcraftapp.R
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

fun Context.isPermissionGranted(name: String): Boolean {
    return ContextCompat.checkSelfPermission(this, name) == PackageManager.PERMISSION_GRANTED
}

fun Context.shareText(text: String) {
    val sendIntent = Intent(
        Intent.ACTION_SEND
    ).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.callPhoneNumber(phoneNumber: String) {
    val uri = "tel:$phoneNumber".toUri()
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = uri
    }
    this.startActivity(intent)
}

fun Context.openBrowser(url: String) {
    val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
        "https://$url"
    } else {
        url
    }
    val browserIntent = Intent(Intent.ACTION_VIEW, formattedUrl.toUri())
    startActivity(browserIntent)
}

fun Context.shareQRCode(bitmap: Bitmap, title: String = "QR Code") {

    try {
        val cachePath = File(cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "qr_code_${System.currentTimeMillis()}.png")

        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.close()

        val imageUri = FileProvider.getUriForFile(
            this,
            "${packageName}.fileprovider",
            file
        )

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            putExtra(Intent.EXTRA_SUBJECT, title)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(Intent.createChooser(shareIntent, "Share QR Code"))

    } catch (e: Exception) {
        Timber.e(e, "ShareImage :: Error sharing image")
    }
}

/**
 * Copies the given text to the clipboard.
 *
 * @param label A user-visible label for the clip data.
 * @param text The text to copy.
 * @param showToast Whether to show a confirmation toast message. Defaults to true.
 */
fun Context.copyToClipboard(label: String, text: String, showToast: Boolean = false) {
    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(label, text)
    clipboardManager.setPrimaryClip(clipData)

    if (showToast) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            Toast.makeText(
                this,
                String.format("%s %s", getString(R.string.copied_to_clipboard), label),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
}