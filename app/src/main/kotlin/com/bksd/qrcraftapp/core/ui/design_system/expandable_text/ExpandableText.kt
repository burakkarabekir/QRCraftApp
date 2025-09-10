package com.bksd.qrcraftapp.core.presentation.design_system.expandable_text

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.bksd.qrcraftapp.R
import com.bksd.qrcraftapp.core.presentation.design_system.theme.QRCraftAppTheme

private const val ELLIPSIS = "..."
private const val SPACE_BUFFER = " "

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedMaxLines: Int = 6,
) {

    var isExpanded by remember { mutableStateOf(false) }
    var isExpandable by remember { mutableStateOf(false) }
    var cutOffIndex by remember { mutableIntStateOf(0) }

    val clickableTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    val clickableText = stringResource(if (isExpanded) R.string.show_less else R.string.show_more)

    val displayText = remember(text, isExpandable, isExpanded, cutOffIndex, clickableText) {
        buildAnnotatedString {
            when {
                isExpandable && !isExpanded -> {
                    if (cutOffIndex > 0 && cutOffIndex <= text.length) {
                        val truncatedText = text.substring(startIndex = 0, endIndex = cutOffIndex)
                            .trimEnd()
                        append(truncatedText)
                        append(ELLIPSIS)
                        append(SPACE_BUFFER)
                        withStyle(
                            style = SpanStyle(
                                color = clickableTextColor
                            )
                        ) {
                            append(clickableText)
                        }
                    } else {
                        append(text)
                    }
                }

                !isExpanded -> append(text)

                else -> {
                    append(text)
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = clickableTextColor,
                        )
                    ) {
                        append(clickableText)
                    }
                }
            }
        }
    }

    Text(
        text = displayText,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = isExpandable,
                interactionSource = null, // to disable ripple effect
                indication = null
            ) { isExpanded = !isExpanded }
            .animateContentSize(),
        onTextLayout = { result ->
            if (!isExpanded && result.hasVisualOverflow) {
                isExpandable = true
                val lastVisibleLineIndex = collapsedMaxLines - 1
                if (result.lineCount > lastVisibleLineIndex && lastVisibleLineIndex >= 0) {
                    val endIndexOfLastLine = result.getLineEnd(lastVisibleLineIndex)
                    val textToDisplayBeforeShowMore = text.substring(0, endIndexOfLastLine)
                    val showMoreAffix = "$ELLIPSIS $clickableText"
                    var potentialCutOff = textToDisplayBeforeShowMore.length - 1
                    while (potentialCutOff > 0 &&
                        (textToDisplayBeforeShowMore
                            .substring(
                                0,
                                potentialCutOff
                            ) + showMoreAffix).length > textToDisplayBeforeShowMore.length
                    ) {
                        potentialCutOff--
                    }
                    cutOffIndex = textToDisplayBeforeShowMore.substring(
                        0,
                        (textToDisplayBeforeShowMore.length - showMoreAffix.length).coerceAtLeast(0)
                    ).trimEnd().length
                } else {
                    isExpandable = false
                }
            }
            if (isExpanded && result.hasVisualOverflow) {
                isExpandable = true
            }
        },
        maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLines,
    )

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    QRCraftAppTheme {
        ExpandableText(
            text = "This is a very long text that should definitely overflow the default number of lines. " +
                    "Let's add some more content to make sure it triggers the expandable behavior. " +
                    "Repeating this sentence multiple times will help achieve the desired effect. " +
                    "This is a very long text that should definitely overflow the default number of lines. " +
                    "Let's add some more content to make sure it triggers the expandable behavior. " +
                    "Repeating this sentence multiple times will help achieve the desired effect. " +
                    "Final bit of text to ensure overflow.",
        )
    }
}