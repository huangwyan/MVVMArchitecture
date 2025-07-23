package com.imyyq.mvvm.composeBase

/**
 *   Created by HuangWuYan on 2025/7/23
 *   Desc:
 **/
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}