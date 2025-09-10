package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.form.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType

enum class FormInputType(val hint: UiText) {
    TEXT(UiText.StringResource(R.string.text)),
    LINK(UiText.StringResource(R.string.placeholder_url)),
    CONTACT_NAME(UiText.StringResource(R.string.placeholder_name)),
    CONTACT_EMAIL(UiText.StringResource(R.string.placeholder_email)),
    CONTACT_PHONE(UiText.StringResource(R.string.placeholder_phone)),
    PHONE(UiText.StringResource(R.string.placeholder_phone)),
    GEO_LAT(UiText.StringResource(R.string.placeholder_lat)),
    GEO_LON(UiText.StringResource(R.string.placeholder_lon)),
    WIFI_SSID(UiText.StringResource(R.string.placeholder_ssid)),
    WIFI_PASSWORD(UiText.StringResource(R.string.placeholder_password)),
    WIFI_ENCRYPTION(UiText.StringResource(R.string.placeholder_encryption))
}

data class FormInputItem(
    val type: FormInputType,
    val required: Boolean = true,
    val keyboard: KeyboardOptions = KeyboardOptions.Default,
    val singleLine: Boolean = true,
    val value: String = "",
)

fun buildItems(type: QRType) = when (type) {
    QRType.TEXT -> listOf(
        FormInputItem(
            type = FormInputType.TEXT,
            singleLine = false
        )
    )

    QRType.LINK -> listOf(
        FormInputItem(
            type = FormInputType.LINK,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Uri)
        )
    )

    QRType.PHONE -> listOf(
        FormInputItem(
            type = FormInputType.PHONE,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
    )

    QRType.CONTACT -> listOf(
        FormInputItem(type = FormInputType.CONTACT_NAME),
        FormInputItem(
            type = FormInputType.CONTACT_EMAIL,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Email)
        ),
        FormInputItem(
            type = FormInputType.CONTACT_PHONE,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
    )

    QRType.GEO -> listOf(
        FormInputItem(
            type = FormInputType.GEO_LAT,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Number)
        ),
        FormInputItem(
            type = FormInputType.GEO_LON,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    )

    QRType.WIFI -> listOf(
        FormInputItem(type = FormInputType.WIFI_SSID),
        FormInputItem(
            type = FormInputType.WIFI_PASSWORD,
            keyboard = KeyboardOptions(keyboardType = KeyboardType.Password)
        ),
        FormInputItem(type = FormInputType.WIFI_ENCRYPTION)
    )
}