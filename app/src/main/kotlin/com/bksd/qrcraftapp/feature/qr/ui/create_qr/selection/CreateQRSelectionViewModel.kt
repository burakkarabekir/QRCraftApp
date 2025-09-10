package com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CreateQRSelectionViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val eventChannel = Channel<CreateQRSelectionEvent>()
    val events = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(CreateQRSelectionState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CreateQRSelectionState()
        )

    fun onAction(action: CreateQRSelectionAction) {
        when (action) {
            is CreateQRSelectionAction.OnClickType -> {
                viewModelScope.launch {
                    eventChannel.send(CreateQRSelectionEvent.NavigateToCreateQR(action.type))
                }
            }
        }
    }

}