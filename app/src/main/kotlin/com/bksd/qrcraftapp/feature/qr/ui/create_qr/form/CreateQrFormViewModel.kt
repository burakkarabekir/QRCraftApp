package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.ui.base.BaseViewModel
import com.bksd.qrcraftapp.core.ui.util.toBase64
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.FormInputItem
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.FormInputType
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.buildItems
import com.bksd.qrcraftapp.feature.qr.ui.util.QRCodeGenerator
import kotlinx.coroutines.launch

class CreateQrFormViewModel(
    type: QRTypeUi,
) : BaseViewModel<CreateQrFormState, CreateQrFormEvent, CreateQrFormAction>(
    CreateQrFormState(
        uiModel = CreateQRFormUiModel(
            items = buildItems(type.type),
            title = type.textRes,
        )
    )
) {

    val qrType = type
    override fun onAction(action: CreateQrFormAction) {
        when (action) {
            CreateQrFormAction.OnNavigateBackClick -> sendEvent(CreateQrFormEvent.OnNavigateBack)
            is CreateQrFormAction.OnItemTextChange -> setState {
                val updatedItems = uiModel.items.map { item ->
                    if (item.type.name == action.key) {
                        item.copy(value = action.value)
                    } else {
                        item
                    }
                }
                val isFormValid = validateForm(updatedItems)

                copy(
                    uiModel = uiModel.copy(
                        items = updatedItems,
                        isValid = isFormValid
                    )
                )
            }

            CreateQrFormAction.OnGenerateClick -> {
                onGenerateClick()
            }
        }
    }

    private fun onGenerateClick() {
        val payload = buildPayload(qrType)
        var qrBitmap: Bitmap? = null
        viewModelScope.launch { qrBitmap = QRCodeGenerator.generateQRCode(payload) }
        sendEvent(
            CreateQrFormEvent.OnNavigateToPreview(
                qrBitmap = qrBitmap?.toBase64().orEmpty(),
                rawValue = payload,
                qrType = qrType,
                id = null,
                displayValue = payload,
            )
        )
    }

    private fun validateForm(items: List<FormInputItem>): Boolean {
        return items.all { item ->
            if (item.required) {
                item.value.isNotBlank()
            } else {
                true
            }
        }
    }

    fun buildPayload(type: QRTypeUi): String {
        val values = state.value.uiModel.items.associate { it.type.name to it.value }
        return when (type.type) {
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
                    appendLine("FN:$it") // full name
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