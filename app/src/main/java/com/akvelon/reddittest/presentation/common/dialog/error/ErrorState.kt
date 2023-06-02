package com.akvelon.reddittest.presentation.common.dialog.error

import com.akvelon.reddittest.util.UiText

data class ErrorState(
    val isShow: Boolean = false,
    val title: UiText = UiText.DynamicString(""),
    val message: UiText = UiText.DynamicString("")
)
