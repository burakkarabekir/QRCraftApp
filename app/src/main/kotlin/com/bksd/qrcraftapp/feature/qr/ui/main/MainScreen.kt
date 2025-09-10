package com.bksd.qrcraftapp.feature.qr.ui.main

import android.Manifest
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.qrcraftapp.core.ui.design_system.loading.AppLoading
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.ui.util.ObserveAsEvents
import com.bksd.qrcraftapp.core.ui.util.isPermissionGranted
import com.bksd.qrcraftapp.feature.qr.ui.camera.QRCameraPreview
import com.bksd.qrcraftapp.feature.qr.ui.component.CameraPermissionDialog
import com.bksd.qrcraftapp.feature.qr.ui.component.snackbar.PermissionSnackbar
import com.bksd.qrcraftapp.feature.qr.ui.main.mapper.toScanResultUi
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToScanResult: (ScanResultUi) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: MainViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = LocalActivity.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onAction(MainAction.Start)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted -> viewModel.onAction(MainAction.OnCameraPermissionResult(isGranted = granted)) }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            MainEvent.RequestCameraPermission -> permissionLauncher.launch(Manifest.permission.CAMERA)
            MainEvent.CheckCameraPermission -> {
                val hasPermission = context.isPermissionGranted(Manifest.permission.CAMERA)
                viewModel.onAction(MainAction.HasPermission(isGranted = hasPermission))
            }

            MainEvent.CloseApp -> activity?.finishAffinity()
            is MainEvent.OnDoneScanning -> onNavigateToScanResult(event.resultUi)

            is MainEvent.ShowSnack -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    MainContent(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun MainContent(
    state: MainState,
    onAction: (MainAction) -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            PermissionSnackbar(
                snackbarHostState,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                })
        }
    ) { innerPadding ->
        when {
            state.showCameraPermissionDialog && !state.permissionGranted -> {
                CameraPermissionDialog(
                    modifier = Modifier.padding(innerPadding),
                    onConfirmClick = { onAction(MainAction.OnPermissionDialogPositiveClick) },
                    onDismissClick = { onAction(MainAction.OnPermissionDialogNegativeClick) }
                )
            }

            state.readyToScan ->
                Surface {
                    Box(modifier = Modifier.fillMaxSize()) {
                        QRCameraPreview(
                            onQRDetected = { qr ->
                                onAction(MainAction.OnScanProcessing(qr.toScanResultUi()))
                            },
                        )

                        if (state.isScanning) {
                            AppLoading()
                        }
                    }
                }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        MainContent(
            state = MainState(showCameraPermissionDialog = true),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}
