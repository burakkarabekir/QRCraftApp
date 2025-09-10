package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.design_system.button.AppButton
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme

@Composable
fun ScanResultActions(
    onShareClick: () -> Unit = {},
    onCopyClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppButton(
            text = stringResource(R.string.share),
            onClick = onShareClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_share),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null // Redundant with button text
                )
            },
            modifier = Modifier.weight(1f)
        )
        AppButton(
            text = stringResource(R.string.copy),
            onClick = onCopyClick,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_copy),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun Preview() {
    QRCraftAppTheme {
        ScanResultActions()
    }
}