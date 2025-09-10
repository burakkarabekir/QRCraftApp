package com.bksd.qrcraftapp.core.ui.design_system.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import kotlin.run

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = 48.dp,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(horizontal = 24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = if (enabled) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        ),
        modifier = modifier
            .height(height)
            .background(
                color = if (enabled) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
                shape = CircleShape
            ),
    ) {
        leadingIcon?.run {
            invoke()
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = if (enabled) {
                textColor
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    QRCraftAppTheme {
        AppButton(
            text = "Copy",
            onClick = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview
@Composable
fun AppButtonSharePreview() {
    QRCraftAppTheme {
        AppButton(
            text = "Share",
            onClick = {},
            enabled = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Share,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview
@Composable
fun AppButtonWithoutLeadingIconPreview() {
    QRCraftAppTheme {
        AppButton(
            text = "Grant Access",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun AppButtonNegativePreview() {
    QRCraftAppTheme {
        AppButton(
            text = "Grant Access",
            textColor = MaterialTheme.colorScheme.error,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun AppButtonDisabledPreview() {
    QRCraftAppTheme {
        AppButton(
            text = "Grant Access",
            textColor = MaterialTheme.colorScheme.error,
            onClick = {},
            enabled = false
        )
    }
}