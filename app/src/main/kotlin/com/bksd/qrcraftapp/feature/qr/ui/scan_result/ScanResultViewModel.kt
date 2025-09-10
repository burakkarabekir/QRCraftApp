package com.bksd.qrcraftapp.feature.qr.presentation.scan_result

import com.bksd.qrcraftapp.core.presentation.base.BaseViewModel
import com.bksd.qrcraftapp.core.presentation.util.toBitmap
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.presentation.model.ScanResultScreenType
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultAction.OnBackClick
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultAction.OnCopyClick
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultAction.OnShareClick
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultAction.Title
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultEvent.Copy
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.ScanResultEvent.Share
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.mapper.toQR
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultUi

class ScanResultViewModel(
    private val qrDataSource: QRDataSource,
    private val scanResult: ScanResultUi,
) :
    BaseViewModel<ScanResultState, ScanResultEvent, ScanResultAction>(
        ScanResultState(
            uiModel = ScanResultUiModel(
                id = scanResult.qrUi.id,
                screenType = scanResult.screenType,
                scannedValue = scanResult.qrUi.rawValue,
                displayValue = scanResult.qrUi.displayValue,
                format = scanResult.qrUi.format,
                type = scanResult.qrUi.type.type,
                editedText = scanResult.qrUi.title,
                barcodeImage = scanResult.qrUi.getQRBitmap(),
            ),
        )
    ) {

    override fun onAction(action: ScanResultAction) {
        val uiModel = getCurrentState().uiModel
        when (action) {
            is Title.Changed -> setState { copy(uiModel = uiModel.copy(editedText = action.text)) }
            is Title.Saved -> saveTitle(action.text)
            OnCopyClick -> sendEvent(Copy(content = uiModel.scannedValue))
            OnShareClick -> uiModel.barcodeImage?.let { sendEvent(Share(qr = it)) }
            Title.Clicked -> setState { copy(uiModel = uiModel.copy(isEditing = true)) }
            OnBackClick -> {
                if (uiModel.screenType == ScanResultScreenType.SCAN_RESULT && uiModel.isEditing) {
                    uiModel.editedText?.let { saveTitle(it) }
                }
                sendEvent(ScanResultEvent.OnNavigateBack)
            }
        }
    }

    private fun upsert(model: QR) = simpleLaunch {
        qrDataSource.upsert(model)
    }

    private fun saveTitle(title: String) {
        setState { copy(uiModel = uiModel.copy(isEditing = false, editedText = title)) }
        val modelToSave = getCurrentState().uiModel.toQR()
        upsert(modelToSave)
    }
}