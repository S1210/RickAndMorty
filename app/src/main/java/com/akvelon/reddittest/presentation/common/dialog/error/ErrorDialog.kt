package com.akvelon.reddittest.presentation.common.dialog.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.akvelon.reddittest.presentation.common.dialog.Dialog

@Composable
fun ErrorDialog(
    onOkClick: () -> Unit = { },
    state: ErrorState
) {
    Dialog(
        onOkClick = onOkClick,
        isCancelButtonVisible = false,
        title = state.title.asString(),
        isShow = state.isShow,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.message.asString(),
                fontSize = 16.sp,
                color = LocalContentColor.current
            )
        }
    }
}
