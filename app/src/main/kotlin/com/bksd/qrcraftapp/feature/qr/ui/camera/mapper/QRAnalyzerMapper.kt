package com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper

import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.DetectedQRUi
import com.google.mlkit.vision.barcode.common.Barcode
import timber.log.Timber

fun Barcode.toDetectedQRUi() = DetectedQRUi(
    rawValue = rawValue,
    type = valueType,
    format = format,
    displayValue = displayValue
).also { Timber.d("QR detected: ${it.rawValue} :: type ${it.type} :: format ${it.format}") }