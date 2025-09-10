package com.bksd.qrcraftapp.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E, A>(initialState: S) : ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = initialState
        )

    private val _events = Channel<E>(capacity = Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    protected fun sendEvent(event: E) {
        viewModelScope.launch { _events.trySend(event) }
    }
    protected fun getCurrentState(): S = state.value
    protected fun setState(reducer: S.() -> S) {
        _state.update { it.reducer() }
    }

    open fun onAction(action: A) {}

    protected fun simpleLaunch(block: suspend () -> Unit) = viewModelScope.launch { block() }

}