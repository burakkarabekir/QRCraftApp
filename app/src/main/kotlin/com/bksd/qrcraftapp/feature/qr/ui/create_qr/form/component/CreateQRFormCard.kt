package com.bksd.qrcraftapp.feature.qr.presentation.create_qr.form.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.design_system.button.AppButton
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme
import com.bksd.qrcraftapp.core.presentation.util.UiText
import com.bksd.qrcraftapp.feature.qr.domain.model.QRType

@Composable
fun CreateQRFormCard(
    items: List<FormInputItem>,
    modifier: Modifier = Modifier,
    isEnable: Boolean = false,
    onValueChange: (String, String) -> Unit = { _, _ -> },
    onGenerateClick: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = modifier
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.forEach { item ->
                OutlinedTextField(
                    value = item.value,
                    onValueChange = onValueChange.let { change ->
                        { newValue ->
                            change(item.type.name, newValue)
                        }
                    },
                    placeholder = {
                        Text(
                            item.type.hint.asString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    singleLine = item.singleLine,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTextColor = MaterialTheme.colorScheme.inversePrimary,
                        cursorColor = MaterialTheme.colorScheme.onSurface
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.background),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = item.keyboard.keyboardType,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                )
            }
            AppButton(
                text = UiText.StringResource(R.string.generate_qr).asString(),
                onClick = onGenerateClick,
                enabled = isEnable,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        CreateQRFormCard(
            items = buildItems(type = QRType.CONTACT),
        )
    }
}