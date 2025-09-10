package com.bksd.qrcraftapp.feature.qr.ui.history.generated_tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.feature.qr.ui.history.component.EmptyTabContent
import com.bksd.qrcraftapp.feature.qr.ui.model.QRUi

@Composable
fun GeneratedTab(
    modifier: Modifier = Modifier,
    generatedItems: List<QRUi> = emptyList(),
    onAction: () -> Unit = {},
) {
    if (generatedItems.isEmpty()) {
        EmptyTabContent()
    } else {
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(
                items = generatedItems,
                key = { it.id }
            ) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = item.rawValue,
                        modifier = Modifier.padding(16.dp)
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
        GeneratedTab()
    }
}