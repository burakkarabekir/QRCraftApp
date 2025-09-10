package com.bksd.qrcraftapp.feature.qr.ui.scan_result.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme

@Composable
fun ScanResultTitle(
    title: String,
    isEditing: Boolean,
    onValueChange: (String) -> Unit,
    onCommit: (String) -> Unit,
    isEditable: Boolean,
) {
    EditableTitle(
        text = title,
        onValueChange = onValueChange,
        onCommit = onCommit,
        startInEditMode = isEditing,
        isEditable = isEditable
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
            onCommit = {},
            isEditable = true
        )
    }
}
