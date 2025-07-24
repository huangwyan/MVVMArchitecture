/*
package com.imyyq.mvvm.composeBase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.collect

*/
/**
 * Created by HuangWuYan on 2025/7/23
 * Desc: Base activity for MVVM with Compose
 *//*

abstract class BaseActivity : ComponentActivity() {

    protected lateinit var viewModel: BaseViewModel

    abstract fun createViewModel(): Class<out BaseViewModel>

    @Composable
    abstract fun ContentView(viewModel: BaseViewModel)

    @Composable
    fun LoadingView() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ErrorView(message: String, onRetry: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("出错啦：$message", color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onRetry) { Text("重试") }
            }
        }
    }

    @Composable
    fun EmptyView(onRetry: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("暂无内容")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onRetry) { Text("刷新") }
            }
        }
    }

    open fun onRetry() {}

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[createViewModel()]

        setContent {
            val context = LocalContext.current
            var currentDialog by remember { mutableStateOf<DialogEvent?>(null) }

            LaunchedEffect(Unit) {
                viewModel.toast.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            LaunchedEffect(Unit) {
                viewModel.dialogEvent.collect {
                    currentDialog = it
                }
            }

            Surface(modifier = Modifier.fillMaxSize()) {
                when (val state = viewModel.uiState.value) {
                    is UiState.Loading -> LoadingView()
                    is UiState.Error -> ErrorView(state.message) { onRetry() }
                    is UiState.Empty -> EmptyView { onRetry() }
                    is UiState.Success -> ContentView(viewModel)
                }
            }

            currentDialog?.let { event ->
                when (event) {
                    is DialogEvent.Info -> AlertDialog(
                        onDismissRequest = { currentDialog = null },
                        title = { Text(event.title) },
                        text = { Text(event.message) },
                        confirmButton = {
                            TextButton(onClick = { currentDialog = null }) {
                                Text("确定")
                            }
                        }
                    )
                    is DialogEvent.Confirm -> AlertDialog(
                        onDismissRequest = { currentDialog = null },
                        title = { Text(event.title) },
                        text = { Text(event.message) },
                        confirmButton = {
                            TextButton(onClick = {
                                currentDialog = null
                                event.onConfirm()
                            }) {
                                Text("确认")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { currentDialog = null }) {
                                Text("取消")
                            }
                        }
                    )
                    is DialogEvent.Custom -> AlertDialog(
                        onDismissRequest = { currentDialog = null },
                        title = { Text(event.title) },
                        text = { Text(event.message) },
                        confirmButton = {
                            TextButton(onClick = {
                                currentDialog = null
                                event.onConfirm()
                            }) {
                                Text("确定")
                            }
                        }
                    )
                }
            }
        }
    }
}*/
