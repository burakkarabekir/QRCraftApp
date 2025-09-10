package com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BottomFade(
    modifier: Modifier = Modifier,
    heightFraction: Float = 0.2f,
    color: Color = MaterialTheme.colorScheme.background,
) {
    BoxWithConstraints(modifier = modifier) {
        val calculatedHeight = (maxHeight * heightFraction).coerceIn(minHeight, maxHeight)

        Box(
            modifier
                .fillMaxWidth()
                .height(calculatedHeight)
                .drawWithCache {
                    val brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, color),
                        startY = 0f,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush)
                    }
                }
        )
    }
}
