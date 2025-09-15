@file:OptIn(ExperimentalMaterial3Api::class)

package com.bksd.qrcraftapp.feature.qr.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.ui.util.ObserveAsEvents
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.generated_tab.GeneratedTabScreen
import com.bksd.qrcraftapp.feature.qr.ui.history.tabs.scanned_tab.ScannedTabScreen
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = koinViewModel(),
    onNavigateScanResult: (QRUi) -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(HistoryAction.Start)
    }
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is HistoryEvent.OnNavigateScanResult -> onNavigateScanResult(event.model)
        }
    }

    HistoryContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun HistoryContent(
    state: HistoryState,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(pageCount = { 2 }),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onAction: (HistoryAction) -> Unit,
) {
    val tabIndex = pagerState.currentPage
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.scan_history),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            PrimaryTabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.background,
                divider = { HorizontalDivider() },
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        Modifier
                            .tabIndicatorOffset(tabIndex)
                            .padding(horizontal = 16.dp)
                            .height(2.dp)
                            .background(MaterialTheme.colorScheme.onSurface)
                            .fillMaxWidth(),
                        color = Color.Transparent
                    )
                }
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                    text = {
                        Text(
                            stringResource(R.string.history_tab_scanned),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (pagerState.currentPage == 0) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                )
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.history_tab_generated),
                            style = MaterialTheme.typography.labelMedium,
                            color = if (pagerState.currentPage == 1) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                )
            }

            HorizontalPager(
                state = pagerState,
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) { page ->
                when (page) {
                    0 -> ScannedTabScreen(
                        modifier = Modifier.fillMaxSize(),
                        isLoading = state.isLoading,
                        scannedItems = state.scannedQRList,
                        onNavigateScanResult = { onAction(HistoryAction.OnNavigateScanResult(it)) }
                    )

                    1 -> GeneratedTabScreen(
                        modifier = Modifier.fillMaxSize(),
                        generatedItems = state.generatedQRList,
                        onNavigateScanResult = { onAction(HistoryAction.OnNavigateScanResult(it)) }
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        HistoryContent(
            state = HistoryState(isLoading = false),
            onAction = {},
            pagerState = rememberPagerState { 0 }
        )
    }
}