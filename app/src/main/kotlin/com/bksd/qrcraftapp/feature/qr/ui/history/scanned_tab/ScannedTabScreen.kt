package com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.qrcraftapp.core.ui.design_system.loading.AppLoading
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.ui.util.ObserveAsEvents
import com.bksd.qrcraftapp.core.ui.util.shareQRCode
import com.bksd.qrcraftapp.feature.qr.ui.history.component.EmptyTabContent
import com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.component.BottomFade
import com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.component.OptionSelectionSheet
import com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.component.ScannedTabItem
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScannedTabScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    scannedItems: List<QRUi> = emptyList(),
    onNavigateScanResult: (QRUi) -> Unit = {},
    viewModel: ScannedTabViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is ScannedTabEvent.OnNavigateScanResult -> onNavigateScanResult(event.model)
            is ScannedTabEvent.Share -> context.shareQRCode(bitmap = event.qr)
        }
    }
    if (isLoading) {
        AppLoading(contentColor = MaterialTheme.colorScheme.onSurface)
    } else if (scannedItems.isEmpty()) {
        EmptyTabContent()
    } else {
        ScannedTabContent(
            state = state,
            scannedItems = scannedItems,
            modifier = modifier.fillMaxSize(),
            onAction = viewModel::onAction
        )
    }
}

@Composable
fun ScannedTabContent(
    state: ScannedTabState,
    modifier: Modifier = Modifier,
    onAction: (ScannedTabAction) -> Unit = {},
    scannedItems: List<QRUi>,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)
        ) {
            items(items = scannedItems, key = { it.id }) { item ->
                ScannedTabItem(
                    model = item,
                    onClick = { onAction(ScannedTabAction.OnItemClick(item)) },
                    onLongPress = { onAction(ScannedTabAction.OnItemLongPress(item.id)) },
                )
            }
        }

        BottomFade(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f),
        )
        OptionSelectionSheet(
            isVisible = state.showOptionSheet,
            onShareClick = {
                state.selectedItemIdForOptions?.let {
                    onAction(
                        ScannedTabAction.OnShareClick(
                            it
                        )
                    )
                }
            },
            onDeleteClick = {
                state.selectedItemIdForOptions?.let {
                    onAction(
                        ScannedTabAction.OnDeleteClick(
                            it
                        )
                    )
                }
            },
            onDismiss = { onAction(ScannedTabAction.OnDismissBottomSheet) }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        ScannedTabContent(
            state = ScannedTabState(),
            scannedItems = emptyList()
        )
    }
}