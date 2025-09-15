package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.ui.base.BaseViewModel
import com.bksd.qrcraftapp.core.ui.util.toBase64
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.FormInputItem
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.buildItems
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.util.BuildQrPayload
import com.bksd.qrcraftapp.feature.qr.ui.history.mapper.toQR
import com.bksd.qrcraftapp.feature.qr.ui.main.mapper.toQR
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import com.bksd.qrcraftapp.feature.qr.ui.util.QRCodeGenerator
import kotlinx.coroutines.launch
import kotlin.text.insert

class CreateQrFormViewModel(
   private val type: QRTypeUi,
   private val buildQrPayload: BuildQrPayload,
   private val qrDataSource: QRDataSource,
) : BaseViewModel<CreateQrFormState, CreateQrFormEvent, CreateQrFormAction>(
    CreateQrFormState(
        uiModel = CreateQRFormUiModel(
            items = buildItems(type.type),
            title = type.textRes,
        )
    )
) {

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
        val payload = buildQrPayload(type.type, state.value.uiModel.items)
        viewModelScope.launch {
            try {
                val qrBitmap = QRCodeGenerator.generateQRCode(payload)
                val qr = QRUi(
                    rawValue = payload,
                    displayValue = payload,
                    type = type,
                    qrSource = QRSource.GENERATED,
                )
                val qrToSave = qr.toQR()
                val savedId = qrDataSource.insert(qrToSave)

                sendEvent(
                    CreateQrFormEvent.OnNavigateToPreview(
                        qrBitmap = qrBitmap?.toBase64().orEmpty(),
                        rawValue = payload,
                        qrType = type,
                        id = savedId,
                        displayValue = payload,
                    )
                )
            } catch (e: Exception) {
                sendEvent(CreateQrFormEvent.ShowError(e.message ?: "Failed to generate QR code"))
            }
        }
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
}