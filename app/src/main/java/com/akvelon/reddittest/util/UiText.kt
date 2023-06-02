package com.akvelon.reddittest.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {

    data class DynamicString(val value: String): UiText()
    data class StringResource(
        @StringRes val resId: Int,
        val args: List<Any>
    ): UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args.toTypedArray())
        }
    }

}