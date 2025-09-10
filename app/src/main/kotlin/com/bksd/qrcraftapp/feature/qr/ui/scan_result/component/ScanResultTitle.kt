package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme

@Composable
fun ScanResultTitle(
    title: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit,
    onCommit: (String) -> Unit,
) {
    EditableTitle(
        text = title,
        onValueChange = onValueChange,
        onCommit = onCommit,
        startInEditMode = isEditing
    )
}

@Preview
@Composable
fun ScanResultTitlePreview() {
    QRCraftAppTheme {
        ScanResultTitle(
            title = "Sample Title",
            isEditing = false,
            onValueChange = {},
            onCommit = {}
        )
    }
}
