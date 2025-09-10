package com.bksd.qrcraftapp.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.bksd.qrcraftapp.core.ui.design_system.navigation_bar.NavigationBar
import com.bksd.qrcraftapp.core.ui.design_system.navigation_bar.NavigationBarItem
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.CreateQrFormScreen
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection.CreateQRSelectionScreen
import com.bksd.qrcraftapp.feature.qr.ui.history.HistoryScreen
import com.bksd.qrcraftapp.feature.qr.ui.main.MainScreen
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.ScanResultScreen
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultUi
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(HistoryScreenRoute)
    val currentEntry = backStack.last()
    val showBottomBar = currentEntry is NavigationBarItem

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    NavigationBar(
                        onNavigate = { route ->
                            backStack.add(route)
                        },
                        current = currentEntry
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier
                .then(
                    if (currentEntry == HistoryScreenRoute) Modifier
                    else Modifier.padding(innerPadding)
                ),
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                rememberSceneSetupNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<MainScreenRoute> { key ->
                    MainScreen(
                        onNavigateToScanResult = {
                            backStack.add(
                                ScanResultScreenRoute(
                                    screenType = it.screenType,
                                    type = it.qrUi.type.type,
                                    id = it.qrUi.id,
                                    rawValue = it.qrUi.rawValue,
                                    displayValue = it.qrUi.displayValue,
                                    barcodeImage = it.qrUi.generatedQRPayload(),
                                    format = it.qrUi.format,
                                    title = it.qrUi.title.orEmpty(),
                                )
                            )
                        }
                    )
                }
                entry<CreateQRScreenRoute> { key ->
                    CreateQRSelectionScreen(
                        onClick = { type ->
                            backStack.add(CreateQrFormScreenRoute(type = type.type))
                        }
                    )
                }
                entry<HistoryScreenRoute> { key ->
                    HistoryScreen(
                        onNavigateScanResult = {
                            backStack.add(
                                ScanResultScreenRoute(
                                    screenType = ScanResultScreenType.SCAN_RESULT,
                                    type = it.type.type,
                                    title = it.title.orEmpty(),
                                    rawValue = it.rawValue,
                                    displayValue = it.displayValue,
                                    barcodeImage = it.generatedQRPayload(),
                                    id = it.id,
                                )
                            )
                        }
                    )
                }
                entry<ScanResultScreenRoute> { key ->
                    ScanResultScreen(
                        viewModel = koinViewModel {
                            parametersOf(
                                ScanResultUi(
                                    screenType = key.screenType,
                                    qrUi = QRUi(
                                        id = key.id ?: 0L,
                                        type = key.type.toUi(),
                                        title = key.title,
                                        rawValue = key.rawValue,
                                        displayValue = key.displayValue,
                                        format = key.format,
                                        timestamp = null,
                                        qrSource = QRSource.SCANNED
                                    )
                                )
                            )
                        },
                        onNavigateBack = { backStack.removeLastOrNull() }
                    )
                }
                entry<CreateQrFormScreenRoute> { key ->
                    CreateQrFormScreen(
                        viewModel = koinViewModel { parametersOf(key.type.toUi()) },
                        onNavigateBack = { backStack.removeLastOrNull() },
                        onNavigateToPreview = {
                            backStack.removeLast()
                            backStack.add(
                                ScanResultScreenRoute(
                                    screenType = it.screenType,
                                    type = it.qrUi.type.type,
                                    rawValue = it.qrUi.rawValue,
                                    displayValue = it.qrUi.displayValue,
                                    barcodeImage = it.qrUi.generatedQRPayload(),
                                    format = it.qrUi.format,
                                    title = it.qrUi.title.orEmpty(),
                                    id = it.qrUi.id
                                )
                            )
                        },
                    )
                }
            }
        )
    }
}