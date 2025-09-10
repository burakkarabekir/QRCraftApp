package com.bksd.qrcraftapp.feature.qr.presentation.scan_result.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bksd.qrcraftapp.core.presentation.design_system.theme.AppColors.editableTextPlaceholder
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme

private const val TEXT_MAX_LENGTH = 32

@Composable
fun EditableTitle(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onCommit: (String) -> Unit = {},
    startInEditMode: Boolean = false,
) {
    var localText by remember { mutableStateOf(text) }
    var isEditing by remember { mutableStateOf(startInEditMode) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isEditing) {
        if (isEditing) {
            focusRequester.requestFocus()
            keyboardController?.show()
        } else {
            focusManager.clearFocus(force = true)
        }
    }
    if (!isEditing) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (!isEditing) {
                                isEditing = true
                                localText = ""
                            }
                        }
                    )
                }
        )
    } else {
        BasicTextField(
            value = localText,
            onValueChange = {
                if (it.length <= TEXT_MAX_LENGTH) {
                    localText = it
                    onValueChange(it)
                }
            },
            singleLine = false,
            textStyle = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    isEditing = false
                    onCommit(localText.trim().ifBlank { text })
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }),
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            decorationBox = { inner ->
                if (localText.isBlank()) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.editableTextPlaceholder,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
                inner()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditableTitlePreview() {
    QRCraftAppTheme {
        EditableTitle(
            text = "Text",
        )
    }
}