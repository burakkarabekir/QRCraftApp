package com.bksd.qrcraftapp.feature.qr.ui.camera.util

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toDetectedQRUi
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.DetectedQRUi
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import timber.log.Timber

class QRAnalyzer(
    private val onQRDetected: (DetectedQRUi) -> Unit,
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()
    private val scanner = BarcodeScanning.getClient(options)

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        try {
            val image = imageProxy.image
            if (image == null) return
            scanner
                .process(InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees))
                .addOnSuccessListener { qrCodes ->
                    qrCodes.firstOrNull()?.let { qrCode ->
                        onQRDetected(qrCode.toDetectedQRUi())
                    }
                }
                .addOnFailureListener { t -> Timber.e("QR scanning failed: $t") }
        } finally {
            imageProxy.close()
        }
    }
}