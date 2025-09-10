@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.component

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.model.OptionSelectionType

@Composable
fun OptionSelectionSheet(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    bitmapToShare: Bitmap? = null,
    onShareClick: (Bitmap?) -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(isVisible) {
        if (isVisible) {
            sheetState.show()
        } else {
            sheetState.hide()
            onDismiss
        }
    }
    if (!sheetState.isVisible && !isVisible) {
        return
    }

    ModalBottomSheet(
        sheetState = sheetState,
        shape = RoundedCornerShape(16.dp),
        contentColor = MaterialTheme.colorScheme.surface,
        containerColor = MaterialTheme.colorScheme.surface,
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .navigationBarsPadding()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OptionSheetRow(type = OptionSelectionType.SHARE, onClick = {
                onShareClick(bitmapToShare)
            })
            OptionSheetRow(type = OptionSelectionType.DELETE, onClick = { onDeleteClick() })
        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        OptionSelectionSheet(isVisible = true)
    }
}