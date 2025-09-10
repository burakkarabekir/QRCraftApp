package com.bksd.qrcraftapp.feature.qr.presentation.history.scanned_tab.component

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.presentation.model.QRUi
import java.time.Instant

@Composable
fun ScannedTabItem(
    model: QRUi,
    modifier: Modifier = Modifier,
    contentMaxLines: Int = 2,
    onClick: () -> Unit = {},
    onLongPress: () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 1.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .combinedClickable(
                    onClick = { onClick() },
                    onLongClick = { onLongPress() }
                )
                .padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .background(
                        color = model.type.containerColor,
                        shape = CircleShape
                    )
                    .padding(8.dp),
                imageVector = ImageVector.vectorResource(model.type.icon),
                tint = model.type.contentColor,
                contentDescription = model.type.text.asString(),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = model.title.ifBlank { model.type.text.asString() },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = model.displayValue,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = contentMaxLines
                )
                Text(
                    text = model.formattedTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        ScannedTabItem(
            model = QRUi(
                type = QRType.TEXT.toUi(),
                title = "Edited Title",
                rawValue = "Adipiscing ipsum lacinia tincidunt sed. In risus dui accumsan accumsan quam morbi nulla. Dictum justo metus auctor nunc quam id sed. Urna nisi gravida sed lobortis diam pretium.",
                displayValue = "Adipiscing ipsum lacinia tincidunt sed. In risus dui accumsan accumsan quam morbi nulla. Dictum justo metus auctor nunc quam id sed. Urna nisi gravida sed lobortis diam pretium.",
                timestamp = Instant.now(),
                qrSource = QRSource.SCANNED,
                id = 0,
            )
        )
    }
}