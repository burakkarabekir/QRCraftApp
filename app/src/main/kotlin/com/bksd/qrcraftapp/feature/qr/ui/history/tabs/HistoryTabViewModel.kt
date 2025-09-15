package com.bksd.qrcraftapp.feature.qr.ui.history.tabs

import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.ui.base.BaseViewModel
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.ui.util.QRCodeGenerator.encodeBitmap
import com.bksd.qrcraftapp.feature.qr.ui.util.toZxingFormat
import kotlinx.coroutines.launch

class HistoryTabViewModel(
    private val qrDataSource: QRDataSource,
) : BaseViewModel<ScannedTabState, HistoryTabEvent, HistoryTabAction>(ScannedTabState()) {

    override fun onAction(action: HistoryTabAction) {
        when (action) {
            HistoryTabAction.OnDismissBottomSheet -> {
                setState {
                    copy(
                        uiModel = uiModel.copy(showOptionSheet = false)
                    )
                }
            }

            is HistoryTabAction.OnDeleteClick -> {
                delete(action.id)
                setState {
                    copy(
                        uiModel = uiModel.copy(showOptionSheet = false))
                }
            }

            is HistoryTabAction.OnItemClick -> {
                sendEvent(HistoryTabEvent.OnNavigateScanResult(action.model))
            }

            is HistoryTabAction.OnItemLongPress -> {
                setState {
                    copy(
                        uiModel = uiModel.copy( showOptionSheet = true,
                        selectedItemIdForOptions = action.id
                        )
                    )
                }
            }

            is HistoryTabAction.OnShareClick -> {
                viewModelScope.launch {
                    qrDataSource.getQrEntityById(action.id).collect { model ->
                        encodeBitmap(
                            model.rawValue,
                            toZxingFormat(model.format),
                        )?.let {
                            sendEvent(HistoryTabEvent.Share(it))
                        }
                    }
                }
                setState {
                    copy(
                        uiModel = uiModel.copy(showOptionSheet = false))
                }
            }
        }
    }

    private fun delete(id: Long) = viewModelScope.launch {
        qrDataSource.delete(id)
    }
}