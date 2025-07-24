package com.imyyq.mvvm.composeBase

/**
 * Created by HuangWuYan on 2025/7/23
 * Desc: UI state definitions
 */
sealed class UiState {
    object Loading : UiState()
    data class Error(val message: String) : UiState()
    object Empty : UiState()
    object Success : UiState()
}