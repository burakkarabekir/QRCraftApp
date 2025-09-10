package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.form

import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.presentation.create_qr.form.component.FormInputItem

data class CreateQrFormState(
    val uiModel: CreateQRFormUiModel = CreateQRFormUiModel(),
)

data class CreateQRFormUiModel(
    val title: UiText = UiText.Dynamic(""),
    val isValid: Boolean = false,
    val items: List<FormInputItem> = emptyList(),
)