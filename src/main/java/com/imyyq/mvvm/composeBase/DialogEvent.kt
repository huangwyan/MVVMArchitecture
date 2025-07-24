package com.imyyq.mvvm.composeBase

import androidx.compose.runtime.Composable

/**
 * Created by HuangWuYan on 2025/7/23
 * Desc: Event types for dialogs
 */
sealed class DialogEvent {
    data class Info(val title: String, val message: String) : DialogEvent()
    data class Confirm(val title: String, val message: String, val onConfirm: () -> Unit) : DialogEvent()
    data class Custom(val title: String, val message: String, val onConfirm: () -> Unit, @get:Composable val content: @Composable () -> Unit) : DialogEvent()
}