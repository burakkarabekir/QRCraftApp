package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form

import androidx.annotation.StringRes
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.FormInputItem

data class CreateQrFormState(
    val uiModel: CreateQRFormUiModel = CreateQRFormUiModel(),
)

data class CreateQRFormUiModel(
    @get:StringRes val title: Int = 0,
    val isValid: Boolean = false,
    val items: List<FormInputItem> = emptyList(),
)