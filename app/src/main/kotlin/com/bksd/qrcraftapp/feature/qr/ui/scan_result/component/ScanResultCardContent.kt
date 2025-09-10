package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardActions
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardState
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardStyle


@Composable
fun ScanResultCardContent(
    state: ScanResultCardState,
    actions: ScanResultCardActions,
    style: ScanResultCardStyle,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(
                top = style.tileOverlap + 24.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScanResultTitle(
                title = state.title.ifBlank { state.resultType.text.asString() },
                isEditing = state.isEditing,
                onValueChange = actions.onTitleValueChange,
                onCommit = actions.readyToSave
            )

            Spacer(Modifier.height(10.dp))

            ScanResultDescription(
                resultType = state.resultType.type,
                description = state.resultDescription
            )

            Spacer(Modifier.height(24.dp))

            ScanResultActions(
                onShareClick = actions.onShareClick,
                onCopyClick = actions.onCopyClick
            )
        }
    }
}

@Preview
@Composable
fun ScanResultCardContentPreview() {
    val state = ScanResultCardState(
        resultType = QRType.TEXT.toUi(),
        title = "Sample Title",
        resultDescription = UiText.Dynamic("Sample description for the scan result."),
        isEditing = false,
        image = null
    )
    val actions = ScanResultCardActions()
    val style = ScanResultCardStyle()

    ScanResultCardContent(state = state, actions = actions, style = style)
}
