@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)

package com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.ui.util.ObserveAsEvents
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.ui.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.ui.camera.model.QRTypeUi
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection.component.CreateQRCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateQRSelectionScreen(
    onClick: (QRTypeUi) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateQRSelectionViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val activity = LocalActivity.current
    val windowSizeClass = activity?.let { calculateWindowSizeClass(it) }

    val itemsPerRow = when (windowSizeClass?.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 2 // Mobile portrait
        WindowWidthSizeClass.Medium -> 3  // Mobile landscape or small tablet
        WindowWidthSizeClass.Expanded -> 4 // Large tablet or desktop
        else -> 2
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is CreateQRSelectionEvent.NavigateToCreateQR -> onClick(event.type)
        }
    }

    CreateQRSelectionContent(
        itemsPerRow = itemsPerRow,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun CreateQRSelectionContent(
    itemsPerRow: Int,
    onAction: (CreateQRSelectionAction) -> Unit,
    modifier: Modifier = Modifier,
) {
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
                        text = stringResource(R.string.create_qr),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                },
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(itemsPerRow),
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = QRType.entries.map { it.toUi() },
                    key = { it.type.name }) { type ->
                    CreateQRCard(
                        type = type,
                        onClick = { onAction(CreateQRSelectionAction.OnClickType(type)) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    QRCraftAppTheme {
        CreateQRSelectionContent(
            onAction = {},
            itemsPerRow = 2,
        )
    }
}