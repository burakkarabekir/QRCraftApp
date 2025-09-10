package com.bksd.qrcraftapp.feature.qr.presentation.history.scanned_tab

import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.presentation.base.BaseViewModel
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.presentation.util.QRCodeGenerator.encodeBitmap
import com.bksd.qrcraftapp.feature.qr.presentation.util.toZxingFormat
import kotlinx.coroutines.launch

class ScannedTabViewModel(
    private val qrDataSource: QRDataSource,
) : BaseViewModel<ScannedTabState, ScannedTabEvent, ScannedTabAction>(ScannedTabState()) {

    override fun onAction(action: ScannedTabAction) {
        when (action) {
            ScannedTabAction.OnDismissBottomSheet -> {
                setState {
                    copy(showOptionSheet = false)
                }
            }

            is ScannedTabAction.OnDeleteClick -> {
                delete(action.id)
                setState {
                    copy(showOptionSheet = false)
                }
            }

            is ScannedTabAction.OnItemClick -> {
                sendEvent(ScannedTabEvent.OnNavigateScanResult(action.model))
            }

            is ScannedTabAction.OnItemLongPress -> {
                setState {
                    copy(
                        showOptionSheet = true,
                        selectedItemIdForOptions = action.id
                    )
                }
            }

            is ScannedTabAction.OnShareClick -> {
                viewModelScope.launch {
                    qrDataSource.getQrEntityById(action.id).collect { model ->
                        encodeBitmap(
                            model.rawValue,
                            toZxingFormat(model.format),
                        )?.let {
                            sendEvent(ScannedTabEvent.Share(it))
                        }
                    }
                }
                setState {
                    copy(showOptionSheet = false)
                }
            }
        }
    }

    private fun delete(id: Int) = viewModelScope.launch {
        qrDataSource.delete(id)
    }
}