package com.bksd.qrcraftapp.feature.qr.presentation.history

import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.presentation.base.BaseViewModel
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.presentation.history.mapper.toQRUi
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
        qrDataSource.observeScannedQRList()
            .onStart { setState { copy(isLoading = true) } }
            .catch { setState { copy(isLoading = false) } }
            .collect { qrList ->
                setState {
                    copy(
                        isLoading = false,
                        qrList = qrList.map { it.toQRUi() })
                }
            }
    }
}