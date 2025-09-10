package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form

sealed interface CreateQrFormAction {
    data object OnNavigateBackClick : CreateQrFormAction
    data object OnGenerateClick : CreateQrFormAction
    data class OnItemTextChange(val key: String, val value: String) : CreateQrFormAction
}