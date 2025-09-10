package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.presentation.design_system.expandable_text.ExpandableText
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi

@Composable
fun ScanResultDescription(
    resultType: QRType,
    description: UiText,
) {
    when (resultType) {
        QRType.TEXT -> {
            ExpandableText(
                text = description.asString(),
                modifier = Modifier.fillMaxWidth(),
            )
        }

        QRType.LINK -> {
            Text(
                text = description.asString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 2.dp)
                    .wrapContentWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        else -> {
            Text(
                text = description.asString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanResultDescriptionPreview() {
    QRCraftAppTheme {
        ScanResultDescription(
            resultType = QRType.TEXT,
            description = UiText.Dynamic(
                value = "This is a sample description for the preview."
            )
        )
    }
}



@Preview(showBackground = true)
@Composable
fun LinkDescriptionPreview() {
    QRCraftAppTheme {
        ScanResultDescription(
            resultType = QRType.LINK,
            description = UiText.Dynamic(
                value = "www.google.com"
            )
        )
    }
}

