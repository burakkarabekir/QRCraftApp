package com.bksd.qrcraftapp.feature.qr.ui.history

import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.ui.base.BaseViewModel
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.ui.history.mapper.toQRUi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val qrDataSource: QRDataSource,
) : BaseViewModel<HistoryState, HistoryEvent, HistoryAction>(HistoryState()) {

    override fun onAction(action: HistoryAction) {
        when (action) {
            HistoryAction.Start -> observeQRList()
            is HistoryAction.OnNavigateScanResult -> sendEvent(
                HistoryEvent.OnNavigateScanResult(
                    action.model
                )
            )
        }
    }

    private fun observeQRList() = viewModelScope.launch {
        qrDataSource.observeQRList()
            .onStart { setState { copy(isLoading = true) } }
            .catch { setState { copy(isLoading = false) } }
            .collect { qrList ->
                setState {
                    copy(
                        isLoading = false,
                        scannedQRList = qrList
                            .filter { it.qrSource == QRSource.SCANNED }
                            .map { it.toQRUi() },
                        generatedQRList = qrList
                            .filter { it.qrSource == QRSource.GENERATED }
                            .map { it.toQRUi() }
                    )
                }
            }
    }
}