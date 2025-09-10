package com.bksd.qrcraftapp.feature.qr.ui.main

import androidx.lifecycle.viewModelScope
import com.bksd.qrcraftapp.core.ui.base.BaseViewModel
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.ui.main.MainEvent.CloseApp
import com.bksd.qrcraftapp.feature.qr.ui.main.MainEvent.OnDoneScanning
import com.bksd.qrcraftapp.feature.qr.ui.main.MainEvent.RequestCameraPermission
import com.bksd.qrcraftapp.feature.qr.ui.main.mapper.toQR
import kotlinx.coroutines.launch

class MainViewModel(
    private val qrDataSource: QRDataSource,
) : BaseViewModel<MainState, MainEvent, MainAction>(MainState()) {

    override fun onAction(action: MainAction) {
        when (action) {
            MainAction.Start -> checkCameraPermission()
            MainAction.OnPermissionDialogPositiveClick -> requestCameraPermission()
            MainAction.OnPermissionDialogNegativeClick -> {
                setState {
                    copy(
                        permissionDenied = true,
                        showCameraPermissionDialog = false
                    )
                }
                sendEvent(CloseApp)
            }

            is MainAction.OnCameraPermissionResult -> when (action.isGranted) {
                true -> {
                    setState {
                        copy(
                            isCameraActive = true,
                            permissionGranted = true,
                            showCameraPermissionDialog = false
                        )
                    }
                }

                false -> sendEvent(CloseApp)
            }

            is MainAction.HasPermission -> when (action.isGranted) {
                true -> {
                    setState {
                        copy(
                            isCameraActive = true,
                            permissionGranted = true,
                            showCameraPermissionDialog = false
                        )
                    }
                }

                false -> setState {
                    copy(
                        isCameraActive = false,
                        permissionGranted = false, showCameraPermissionDialog = true
                    )
                }
            }

            is MainAction.OnScanProcessing -> {
                setState { copy(isScanning = true) }
                viewModelScope.launch {
                    val savedId = qrDataSource.insert(action.resultUi.toQR())
                    sendEvent(
                        OnDoneScanning(
                            action.resultUi.copy(
                                qrUi = action.resultUi.qrUi.copy(
                                    id = savedId
                                )
                            )
                        )
                    )
                }
                setState { copy(isScanning = false) }
            }
        }
    }

    private fun requestCameraPermission() {
        setState { copy(showCameraPermissionDialog = false) }
        sendEvent(RequestCameraPermission)
    }


    private fun checkCameraPermission() {
        sendEvent(MainEvent.CheckCameraPermission)
    }
}