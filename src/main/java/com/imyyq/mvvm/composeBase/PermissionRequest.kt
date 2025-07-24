/*
package com.imyyq.mvvm.composeBase

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.rememberLauncherForActivityResult

*/
/**
 *   Created by HuangWuYan on 2025/7/23
 *   Desc:
 **//*

data class PermissionRequest(
    val permissions: List<String>,
    val rationale: String = "该功能需要权限",
    val onGranted: () -> Unit,
    val onDenied: (() -> Unit)? = null
)

@Composable
fun HandlePermission(request: PermissionRequest) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { result ->
            if (result.all { it.value }) request.onGranted()
            else request.onDenied?.invoke()
                ?: Toast.makeText(context, "权限被拒绝", Toast.LENGTH_SHORT).show()
        }
    )

    LaunchedEffect(Unit) {
        val denied = request.permissions.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }
        if (denied.isEmpty()) request.onGranted()
        else launcher.launch(denied.toTypedArray())
    }
}*/
