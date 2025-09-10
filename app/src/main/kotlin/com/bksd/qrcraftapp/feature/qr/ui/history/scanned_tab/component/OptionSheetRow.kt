package com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.component

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.model.OptionSelectionType

@Composable
fun OptionSheetRow(
    type: OptionSelectionType,
    modifier: Modifier = Modifier,
    bitmapToShare: Bitmap? = null,
    onClick: (Bitmap?) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable { bitmapToShare?.let { onClick(it) } ?: onClick.invoke(null) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp),
            imageVector = ImageVector.vectorResource(type.iconRes),
            contentDescription = stringResource(type.textRes),
            tint = type.contentColorRes
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(type.textRes),
            style = MaterialTheme.typography.labelLarge,
            color = type.contentColorRes,
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        OptionSheetRow(
            type = OptionSelectionType.SHARE
        )
    }
}