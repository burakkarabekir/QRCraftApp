package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.presentation.util.toBitmap
import com.bksd.qrcraftapp.feature.qr.presentation.scan_result.model.ScanResultCardStyle

@Composable
fun ScanResultQRImage(
    image: Bitmap,
    style: ScanResultCardStyle = ScanResultCardStyle(),
) {
    Surface(
        modifier = Modifier
            .size(style.tileSize)
            .offset(y = -style.tileOverlap)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(style.tileCorner),
                ambientColor = DefaultShadowColor.copy(alpha = 0.3f),
                spotColor = DefaultShadowColor.copy(alpha = 0.3f),
            ),
        shape = RoundedCornerShape(style.tileCorner),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = stringResource(R.string.qr_code_image),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun ScanResultQRImagePreview() {
    QRCraftAppTheme {
        ScanResultQRImage(
            image = createMockQrCodeBitmap(),
        )
    }
}

fun createMockQrCodeBitmap(size: Int = 100, blockSize: Int = 10): Bitmap {
    val pixels = IntArray(size * size)
    val black = Color.Black.toArgb()
    val white = Color.White.toArgb()
    for (y in 0 until size) {
        for (x in 0 until size) {
            val blockX = x / blockSize
            val blockY = y / blockSize
            pixels[y * size + x] = if ((blockX + blockY) % 2 == 0) black else white
        }
    }
    return Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888)
}