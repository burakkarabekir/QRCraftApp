package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.core.presentation.util.toBitmap
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardActions
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardState
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardStyle

@Composable
fun ScanResultCard(
    state: ScanResultCardState,
    modifier: Modifier = Modifier,
    actions: ScanResultCardActions = ScanResultCardActions(),
    style: ScanResultCardStyle = ScanResultCardStyle(),
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        ScanResultCardContent(
            state = state,
            actions = actions,
            style = style
        )

        state.image?.let {
            ScanResultQRImage(
                image = it,
                style = style
            )
        }
    }
}

@Preview
@Composable
private fun ScanResultCardPreview() {
    QRCraftAppTheme {
        Surface {
            ScanResultCard(
                state = ScanResultCardState(
                    resultType = QRType.TEXT.toUi(),
                    title = "Sample Title",
                    resultDescription = UiText.Dynamic("Sample long text content for testing"),
                    isEditing = false,
                    image = createMockQrCodeBitmap()
                )
            )
        }
    }
}

@Preview
@Composable
private fun ScanResultCardLinkPreview() {
    QRCraftAppTheme {
        Surface {
            ScanResultCard(
                state = ScanResultCardState(
                    resultType = QRType.LINK.toUi(),
                    title = "Link",
                    resultDescription = UiText.Dynamic("https://www.google.com"),
                    image = createMockQrCodeBitmap()
                )
            )
        }
    }
}

@Preview
@Composable
private fun ScanResultCardEditingPreview() {
    QRCraftAppTheme {
        Surface {
            ScanResultCard(
                state = ScanResultCardState(
                    resultType = QRType.PHONE.toUi(),
                    title = "Phone Number",
                    resultDescription = UiText.Dynamic("+1 123 456 7890"),
                    isEditing = true,
                    image = createMockQrCodeBitmap()
                )
            )
        }
    }
}