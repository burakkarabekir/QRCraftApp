package com.bksd.qrcraftapp.core.presentation.design_system.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme

@Composable
fun AppLoading(
    modifier: Modifier = Modifier,
    overlayColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.surface,
    indicator: @Composable () -> Unit = {
        CircularProgressIndicator(
            color = contentColor,
            strokeWidth = 4.dp
        )
    },
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(overlayColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            indicator()
            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.loading),
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    QRCraftAppTheme {
        AppLoading()
    }
}