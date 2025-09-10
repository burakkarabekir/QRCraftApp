package com.bksd.qrcraftapp.core.presentation.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
    val uri = Uri.parse("tel:$phoneNumber")
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = uri
    }
    this.startActivity(intent)
}

fun Context.openBrowser(url: String) {
    // for mock data that doesnt include https://
    val formattedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
        "https://$url"
    } else {
        url
    }
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrl))
    startActivity(browserIntent)
}

fun Context.shareQRCode(bitmap: Bitmap, title: String = "QR Code") {

    try {
        // Save bitmap to cache directory
        val cachePath = File(cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "qr_code_${System.currentTimeMillis()}.png")

        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.close()

        // Get URI for the file
        val imageUri = FileProvider.getUriForFile(
            this,
            "${packageName}.fileprovider",
            file
        )

        // Create share intent
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            putExtra(Intent.EXTRA_SUBJECT, title)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        startActivity(Intent.createChooser(shareIntent, "Share QR Code"))

    } catch (e: Exception) {
        // Handle error - show toast or log
        Timber.e("ShareImage :: Error sharing image", e)
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
        // For Android 13 (API 33) and above, the system shows its own toast for clipboard operations.
        // You might want to avoid showing a custom toast on these versions
        // or ensure your custom toast doesn't conflict.
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            Toast.makeText(this, getString(R.string.copied_to_clipboard, label), Toast.LENGTH_SHORT)
                .show()
        }
        // On Android 13+, a system-provided visual confirmation is shown.
        // You could optionally still log or perform other actions here if needed.
    }
}