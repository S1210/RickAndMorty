package com.akvelon.reddittest.presentation.common.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun RoundCornerDialog(
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(shape = RoundedCornerShape(5.dp), content = content)
    }
}
