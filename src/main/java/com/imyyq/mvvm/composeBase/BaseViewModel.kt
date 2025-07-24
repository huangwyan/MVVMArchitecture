package com.imyyq.mvvm.composeBase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by HuangWuYan on 2025/7/23
 * Desc: Base ViewModel for MVVM with Compose
 */
open class BaseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _toast = MutableStateFlow<String?>(null)
    val toast = _toast.asStateFlow()

    private val _dialogEvent = MutableStateFlow<DialogEvent?>(null)
    val dialogEvent = _dialogEvent.asStateFlow()

    fun showToast(message: String) {
        viewModelScope.launch {
            _toast.value = message
        }
    }

    fun showDialog(event: DialogEvent) {
        viewModelScope.launch {
            _dialogEvent.value = event
        }
    }

    fun clearDialog() {
        viewModelScope.launch {
            _dialogEvent.value = null
        }
    }

    fun updateState(state: UiState) {
        viewModelScope.launch {
            _uiState.value = state
        }
    }
}