package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.util

import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.FormInputItem
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.FormInputType

class BuildQrPayload {

    operator fun invoke(type: QRType, formItems: List<FormInputItem>): String {
        val values = formItems.associate { it.type.name to it.value }
        return when (type) {
            QRType.TEXT, QRType.LINK -> values.values.firstOrNull().orEmpty()
            QRType.PHONE -> "tel:${values.values.firstOrNull().orEmpty()}"
            QRType.GEO -> "geo:${values[FormInputType.GEO_LAT.name]},${values[FormInputType.GEO_LON.name]}"
            QRType.WIFI -> """
                WIFI:T:${values[FormInputType.WIFI_ENCRYPTION.name] ?: "WPA"};
                S:${values[FormInputType.WIFI_SSID.name].orEmpty()};
                P:${values[FormInputType.WIFI_PASSWORD.name].orEmpty()};;
            """.trimIndent()
            QRType.CONTACT -> buildString {
                appendLine("BEGIN:VCARD")
                appendLine("VERSION:3.0")
                values[FormInputType.CONTACT_NAME.name]?.takeIf { it.isNotBlank() }?.let {
                    appendLine("FN:$it")
                }
                values[FormInputType.CONTACT_PHONE.name]?.takeIf { it.isNotBlank() }?.let {
                    appendLine("TEL:$it")
                }
                values[FormInputType.CONTACT_EMAIL.name]?.takeIf { it.isNotBlank() }?.let {
                    appendLine("EMAIL:$it")
                }
                appendLine("END:VCARD")
            }
        }
    }
}
