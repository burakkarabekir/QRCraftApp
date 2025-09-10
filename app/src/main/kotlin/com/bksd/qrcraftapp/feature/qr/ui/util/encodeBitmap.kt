package com.bksd.qrcraftapp.feature.qr.presentation.util

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import timber.log.Timber

object QRCodeGenerator {
    suspend fun generateQRCode(
        content: String,
        size: Int = DEFAULT_QR_SIZE,
    ): Bitmap? {
        return encodeBitmap(
            content = content,
            format = BarcodeFormat.QR_CODE,
            width = size,
            height = size,
            errorCorrectionLevel = ErrorCorrectionLevel.M,
            margin = DEFAULT_MARGIN,
            characterSet = DEFAULT_CHARACTER_SET
        )
    }

    fun encodeBitmap(
        content: String?,
        format: BarcodeFormat = BarcodeFormat.QR_CODE,
        width: Int = DEFAULT_QR_SIZE,
        height: Int = DEFAULT_QR_SIZE,
        errorCorrectionLevel: ErrorCorrectionLevel = ErrorCorrectionLevel.M,
        margin: Int = DEFAULT_MARGIN,
        characterSet: String = DEFAULT_CHARACTER_SET,
        customHints: Map<EncodeHintType, Any>? = mapOf(
            EncodeHintType.MARGIN to 1,
            EncodeHintType.CHARACTER_SET to DEFAULT_CHARACTER_SET,
            EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.M
        ),
    ): Bitmap? {
        if (content.isNullOrEmpty()) {
            Timber.w("Content to encode is null or empty. Cannot generate bitmap.")
            return null
        }

        if (width <= 0 || height <= 0) {
            Timber.w("Bitmap width and height must be positive. Width: $width, Height: $height")
            return null
        }

        val hints = mutableMapOf<EncodeHintType, Any>()
        hints[EncodeHintType.CHARACTER_SET] = characterSet
        hints[EncodeHintType.MARGIN] = margin

        if (format == BarcodeFormat.QR_CODE) {
            hints[EncodeHintType.ERROR_CORRECTION] = errorCorrectionLevel
        }

        customHints?.let { hints.putAll(it) }

        return try {
            val matrix = MultiFormatWriter().encode(content, format, 0, 0, hints)
            val matrixWidth = matrix.width
            val matrixHeight = matrix.height
            val bitmap = createBitmap(width, height)
            Timber.d("Created bitmap: ${bitmap.width}x${bitmap.height}")

            for (x in 0 until width) {
                for (y in 0 until height) {
                    val sourceX = x * matrixWidth / width
                    val sourceY = y * matrixHeight / height
                    bitmap[x, y] = if (matrix[sourceX, sourceY]) {
                        0xFF000000.toInt()
                    } else 0xFFFFFFFF.toInt()
                }
            }
            bitmap
        } catch (e: WriterException) {
            Timber.e(e, "Could not encode barcode due to WriterException")
            null
        } catch (e: IllegalArgumentException) {
            Timber.e(
                e,
                "Could not encode barcode due to IllegalArgumentException (e.g., invalid content for format)"
            )
            null
        } catch (e: Exception) {
            Timber.e(e, "An unexpected error occurred during bitmap encoding")
            null
        }
    }

    private const val DEFAULT_CHARACTER_SET = "UTF-8"
    private const val DEFAULT_MARGIN = 1
    private const val DEFAULT_QR_SIZE = 512

}