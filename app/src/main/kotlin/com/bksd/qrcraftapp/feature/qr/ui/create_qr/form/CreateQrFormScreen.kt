@file:OptIn(ExperimentalMaterial3Api::class)

package com.bksd.qrcraftapp.feature.qr.ui.create_qr.form

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.CreateQRFormCard
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.component.buildItems
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi
import com.bksd.qrcraftapp.feature.qr.ui.model.ScanResultScreenType
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.model.ScanResultUi
import org.koin.androidx.compose.koinViewModel
import kotlin.math.max

@Composable
fun CreateQrFormScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPreview: (ScanResultUi) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateQrFormViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            CreateQrFormEvent.OnNavigateBack -> onNavigateBack()
            is CreateQrFormEvent.ShowError -> Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
            is CreateQrFormEvent.OnNavigateToPreview -> onNavigateToPreview(
                ScanResultUi(
                    screenType = ScanResultScreenType.PREVIEW,
                    qrUi = QRUi(
                        rawValue = event.rawValue,
                        displayValue = event.displayValue,
                        type = event.qrType,
                        id = event.id ?: 0L,
                        title = context.getString(event.qrType.textRes),
                        qrSource = QRSource.GENERATED,
                    ),
                )
            )
        }
    }

    CreateQrFormContent(
        uiModel = state.uiModel,
        modifier = modifier,
        onAction = viewModel::onAction,
    )
}

@Composable
fun CreateQrFormContent(
    uiModel: CreateQRFormUiModel,
    modifier: Modifier = Modifier,
    onAction: (CreateQrFormAction) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {
                    Text(
                        text = stringResource(uiModel.title),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(CreateQrFormAction.OnNavigateBackClick)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
        Column(
            modifier = modifier
                .widthIn(max = 480.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CreateQRFormCard(
                isEnable = uiModel.isValid,
                items = uiModel.items,
                onValueChange = { key, value ->
                    onAction(CreateQrFormAction.OnItemTextChange(key, value))
                },
                onGenerateClick = {
                    onAction(CreateQrFormAction.OnGenerateClick)
                },
            )
        }
    }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        CreateQrFormContent(
            uiModel = CreateQRFormUiModel(
                items = buildItems(QRType.CONTACT),
                title = QRTypeUi(
                    type = QRType.CONTACT,
                    textRes = QRType.CONTACT.toUi().textRes,
                    icon = 0,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ).textRes,
                isValid = false,
            ),
            onAction = {},
        )
    }
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=320", showSystemUi = true)
@Composable
private fun CreateQRFormScreenTabletPreview() {
    QRCraftAppTheme {
        CreateQrFormContent(
            uiModel = CreateQRFormUiModel(
                items = buildItems(QRType.CONTACT),
                title = QRTypeUi(
                    type = QRType.CONTACT,
                    textRes = QRType.CONTACT.toUi().textRes,
                    icon = 0,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ).textRes,
                isValid = false,
            ),
            onAction = {},
        )
    }
}