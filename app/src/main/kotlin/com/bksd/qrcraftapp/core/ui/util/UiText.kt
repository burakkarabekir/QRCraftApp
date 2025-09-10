package com.bksd.qrcraftapp.core.ui.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource

@Stable
sealed interface UiText {
    data class Dynamic(val value: String) : UiText
    data object Empty : UiText {
        override fun toString(): String = ""
    }
    @Stable
    data class StringResource(
        @field:StringRes val id: Int,
        val args: Array<Any> = emptyArray(),
    ) : UiText

    @Stable
    data class Combined(
        val format: String,
        val uiTexts: Array<UiText>,
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            Empty -> Empty.toString()
            is Dynamic -> value
            is StringResource -> stringResource(id, *args)
            is Combined -> {
                val strings = uiTexts.map { uiText ->
                    when (uiText) {
                        Empty -> UiText.Empty.toString()
                        is Combined -> throw IllegalArgumentException("Cannot nest Combined UiText.")
                        is Dynamic -> uiText.value
                        is StringResource -> stringResource(uiText.id, *uiText.args)
                    }
                }
                String.format(format, *strings.toTypedArray())
            }
        }
    }
}