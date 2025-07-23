package com.imyyq.mvvm.composeBase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 *   Created by HuangWuYan on 2025/7/23
 *   Desc:
 **/
open class BaseViewModel : ViewModel() {

    private val _uiState = mutableStateOf<UiState<Any>>(UiState.Idle)
    val uiState: State<UiState<Any>> = _uiState

    private val _toast = MutableSharedFlow<String>()
    val toast = _toast.asSharedFlow()

    private val _dialogEvent = MutableSharedFlow<DialogEvent>()
    val dialogEvent = _dialogEvent.asSharedFlow()

    fun showLoading() { _uiState.value = UiState.Loading }
    fun showEmpty() { _uiState.value = UiState.Empty }
    fun showError(msg: String) { _uiState.value = UiState.Error(msg) }
    fun <T> showSuccess(data: T) { _uiState.value = UiState.Success(data as Any) }
    fun reset() { _uiState.value = UiState.Idle }

    suspend fun showToast(msg: String) {
        _toast.emit(msg)
    }

    suspend fun showInfoDialog(title: String, msg: String) {
        _dialogEvent.emit(DialogEvent.Info(title, msg))
    }

    suspend fun showConfirmDialog(title: String, msg: String, onConfirm: () -> Unit) {
        _dialogEvent.emit(DialogEvent.Confirm(title, msg, onConfirm))
    }

    suspend fun showCustomDialog(content: @Composable () -> Unit) {
        _dialogEvent.emit(DialogEvent.Custom(content))
    }
}