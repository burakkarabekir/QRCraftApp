package com.bksd.qrcraftapp.feature.qr.ui.history.tabs.scanned_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.component.BottomFade
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.component.OptionSelectionSheet
import com.bksd.qrcraftapp.feature.qr.ui.history.component.HistoryListItem
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.HistoryTabAction
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.HistoryTabEvent
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.ScannedTabUiModel
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.HistoryTabViewModel
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScannedTabScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    scannedItems: List<QRUi> = emptyList(),
    onNavigateScanResult: (QRUi) -> Unit = {},
    viewModel: HistoryTabViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is HistoryTabEvent.OnNavigateScanResult -> onNavigateScanResult(event.model)
            is HistoryTabEvent.Share -> context.shareQRCode(bitmap = event.qr)
        }
    }
    if (isLoading) {
        AppLoading(contentColor = MaterialTheme.colorScheme.onSurface)
    } else if (scannedItems.isEmpty()) {
        EmptyTabContent()
    } else {
        ScannedTabContent(
            uiModel = state.uiModel,
            scannedItems = scannedItems,
            modifier = modifier.fillMaxSize(),
            onAction = viewModel::onAction
        )
    }
}

@Composable
fun ScannedTabContent(
    uiModel: ScannedTabUiModel,
    modifier: Modifier = Modifier,
    onAction: (HistoryTabAction) -> Unit = {},
    scannedItems: List<QRUi>,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)
                .fillMaxHeight()
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = scannedItems, key = { it.id }) { item ->
                HistoryListItem(
                    model = item,
                    onClick = { onAction(HistoryTabAction.OnItemClick(item)) },
                    onLongPress = { onAction(HistoryTabAction.OnItemLongPress(item.id)) },
                )
            }
        }

        BottomFade(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f),
        )
        OptionSelectionSheet(
            isVisible = uiModel.showOptionSheet,
            onShareClick = {
                uiModel.selectedItemIdForOptions?.let {
                    onAction(
                        HistoryTabAction.OnShareClick(
                            it
                        )
                    )
                }
            },
            onDeleteClick = {
                uiModel.selectedItemIdForOptions?.let {
                    onAction(
                        HistoryTabAction.OnDeleteClick(
                            it
                        )
                    )
                }
            },
            onDismiss = { onAction(HistoryTabAction.OnDismissBottomSheet) }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        ScannedTabContent(
            uiModel = ScannedTabUiModel(),
            scannedItems = emptyList()
        )
    }
}