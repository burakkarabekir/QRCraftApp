@file:OptIn(ExperimentalMaterial3Api::class)

package com.bksd.qrcraftapp.feature.qr.ui.scan_result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.ui.util.ObserveAsEvents
import com.bksd.qrcraftapp.core.ui.util.UiText
import com.bksd.qrcraftapp.core.ui.util.copyToClipboard
import com.bksd.qrcraftapp.core.ui.util.shareQRCode
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.component.ScanResultCard
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultCardActions
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultCardState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScanResultScreen(
    onNavigateBack: () -> Unit,
    viewModel: ScanResultViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    BackHandler(enabled = true) {
        viewModel.onAction(ScanResultAction.OnBackClick)
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            ScanResultEvent.OnNavigateBack -> onNavigateBack()
            is ScanResultEvent.Copy -> context.copyToClipboard(event.content, event.content)
            is ScanResultEvent.Share -> context.shareQRCode(bitmap = event.qr)
            is ScanResultEvent.Save -> {}
        }
    }

    ScanResultContent(
        uiModel = state.uiModel,
        onAction = viewModel::onAction
    )
}

@Composable
fun ScanResultContent(
    uiModel: ScanResultUiModel,
    onAction: (ScanResultAction) -> Unit = {},
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Text(
                        text = stringResource(
                            when (uiModel.screenType) {
                                ScanResultScreenType.SCAN_RESULT -> R.string.title_scan_result
                                ScanResultScreenType.PREVIEW -> R.string.title_preview
                            }
                        ),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(ScanResultAction.OnBackClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.aligned(Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 44.dp, vertical = 120.dp)
        ) {
            val type = QRType.entries.find { it == uiModel.type }?.toUi() ?: QRType.TEXT.toUi()
            ScanResultCard(
                state = ScanResultCardState(
                    resultType = type,
                    title = uiModel.editedText ?: stringResource(type.textRes),
                    resultDescription = UiText.Dynamic(uiModel.displayValue),
                    isEditing = uiModel.isEditing,
                    isEditable = uiModel.screenType == ScanResultScreenType.SCAN_RESULT,
                    image = uiModel.barcodeImage
                ),
                actions = ScanResultCardActions(
                    onTitleValueChange = { onAction(ScanResultAction.Title.Changed(it)) },
                    readyToSave = { onAction(ScanResultAction.Title.Saved(it)) },
                    onShareClick = { onAction(ScanResultAction.OnShareClick) },
                    onCopyClick = { onAction(ScanResultAction.OnCopyClick) }
                )
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        ScanResultContent(
            uiModel = ScanResultUiModel(
                scannedValue = "123123",
                format = 0,
                id = 0,
                barcodeImage = null,
                displayValue = "123123",
                editedText = UiText.Empty.toString(),
                type = QRType.TEXT,
            ),
        )
    }
}