package com.bksd.qrcraftapp.feature.qr.presentation.camera

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.feature.qr.presentation.camera.util.Corner
import com.bksd.qrcraftapp.feature.qr.presentation.camera.util.drawFrameCorner
import kotlin.math.min

@Composable
fun CameraOverlay(
    modifier: Modifier = Modifier,
    fraction: Float = 0.9f,
    maxFrameWidth: Dp = 300.dp,
    strokeDp: Dp = 4.dp,
    cornerLen: Dp = 28.dp,
    cornerRadius: Dp = 18.dp,
    scrimColor: Color = Color.Black.copy(alpha = 0.45f),
    cornerColor: Color = MaterialTheme.colorScheme.primary,
    overlayAlpha: Float = 0.99f,
    hint: Int = R.string.camera_preview_title,
) {
    BoxWithConstraints(modifier.fillMaxSize()) {
        val density = LocalDensity.current

        val screenWpx = with(density) { maxWidth.toPx() }
        val screenHpx = with(density) { maxHeight.toPx() }
        val desiredWpx = min(
            screenWpx * fraction,
            with(density) { maxFrameWidth.toPx() })
        val desiredHpx = desiredWpx

        val left = (screenWpx - desiredWpx) / 2f
        val top = (screenHpx - desiredHpx) / 2f
        val right = left + desiredWpx
        val bottom = top + desiredHpx

        val radius = with(density) { cornerRadius.toPx() }
        val cornerLength = with(density) { cornerLen.toPx() }
        val stroke = with(density) { strokeDp.toPx() }

        Canvas(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(alpha = overlayAlpha)
        ) {
            // scrim
            drawRect(scrimColor)

            // punch the rounded hole
            drawRoundRect(
                color = Color.Transparent,
                topLeft = Offset(left, top),
                size = Size(desiredWpx, desiredHpx),
                cornerRadius = CornerRadius(radius, radius),
                blendMode = BlendMode.Clear
            )

            Corner.entries.forEach { corner ->
                drawFrameCorner(
                    left = left,
                    top = top,
                    right = right,
                    bottom = bottom,
                    radius = radius,
                    cornerLength = cornerLength,
                    stroke = stroke,
                    color = cornerColor,
                    corner = corner
                )
            }
        }

        val topDp = with(density) { top.toDp() }

        Text(
            text = stringResource(hint),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = topDp - 50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CameraOverlayPreview() {
    QRCraftAppTheme {
        CameraOverlay()
    }
}