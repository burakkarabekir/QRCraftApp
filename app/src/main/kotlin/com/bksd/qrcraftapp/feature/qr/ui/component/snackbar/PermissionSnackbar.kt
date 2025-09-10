package com.bksd.qrcraftapp.feature.qr.ui.component.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.ui.design_system.theme.AppColors
import com.bksd.qrcraftapp.core.ui.design_system.theme.AppColors.success
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme

@Composable
fun PermissionSnackbar(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    containerColor: Color = MaterialTheme.colorScheme.success,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    SnackbarHost(hostState) { data ->
        Snackbar(
            containerColor = containerColor,
            contentColor = contentColor,
            modifier = modifier
                .fillMaxWidth(0.7f)
                .padding(
                    bottom = WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding() + 12.dp,
                    start = 16.dp, end = 16.dp
                )
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(6.dp)),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                leadingIcon?.run {
                    invoke()
                    Spacer(Modifier.width(8.dp))
                }

                Text(
                    text = data.visuals.message,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PermissionSnackbarPreview() {
    QRCraftAppTheme {
        val hostState = remember { SnackbarHostState() }
        LaunchedEffect(Unit) {
            hostState.showSnackbar("Permission granted")
        }
        PermissionSnackbar(
            hostState = hostState,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = AppColors.Success
                )
            }
        )
    }
}