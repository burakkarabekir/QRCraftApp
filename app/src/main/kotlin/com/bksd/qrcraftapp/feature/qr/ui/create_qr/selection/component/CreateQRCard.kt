package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.selection.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType
import com.bksd.qrcraftapp.feature.qr.presentation.camera.mapper.toUi
import com.bksd.qrcraftapp.feature.qr.presentation.camera.model.QRTypeUi

@Composable
fun CreateQRCard(
    type: QRTypeUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 20.dp, horizontal = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = true,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(type.icon),
            contentDescription = type.text.asString(),
            tint = type.contentColor,
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = type.containerColor,
                    shape = CircleShape
                )
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = type.text.asString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        CreateQRCard(
            type = QRType.PHONE.toUi(),
            modifier = Modifier.width(182.dp),
            onClick = {}
        )
    }
}