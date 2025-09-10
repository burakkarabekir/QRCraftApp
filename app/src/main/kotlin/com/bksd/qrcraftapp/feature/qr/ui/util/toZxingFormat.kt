package com.bksd.qrcraftapp.feature.qr.presentation.util

import com.google.mlkit.vision.barcode.common.Barcode
import com.google.zxing.BarcodeFormat

fun toZxingFormat(ml: Int): BarcodeFormat = when (ml) {
    Barcode.FORMAT_QR_CODE -> BarcodeFormat.QR_CODE
    Barcode.FORMAT_AZTEC -> BarcodeFormat.AZTEC
    Barcode.FORMAT_PDF417 -> BarcodeFormat.PDF_417
    Barcode.FORMAT_DATA_MATRIX -> BarcodeFormat.DATA_MATRIX
    Barcode.FORMAT_CODE_128 -> BarcodeFormat.CODE_128
    Barcode.FORMAT_CODE_39 -> BarcodeFormat.CODE_39
    Barcode.FORMAT_EAN_13 -> BarcodeFormat.EAN_13
    Barcode.FORMAT_EAN_8 -> BarcodeFormat.EAN_8
    Barcode.FORMAT_UPC_A -> BarcodeFormat.UPC_A
    Barcode.FORMAT_UPC_E -> BarcodeFormat.UPC_E
    Barcode.FORMAT_ITF -> BarcodeFormat.ITF
    else -> BarcodeFormat.QR_CODE // safe default
}