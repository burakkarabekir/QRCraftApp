@file:OptIn(ExperimentalMaterial3Api::class)

package com.bksd.qrcraftapp.feature.qr.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.ui.design_system.button.AppButton
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme

@Composable
fun CameraPermissionDialog(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
) {
    BasicAlertDialog (
        onDismissRequest = { },
        modifier = modifier,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.dialog_title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.dialog_message),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.dialog_action_negative),
                        onClick = onDismissClick,
                        textColor = MaterialTheme.colorScheme.error,
                    )
                    AppButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.dialog_action_positive),
                        onClick = onConfirmClick,
                        textColor = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        CameraPermissionDialog()
    }
}