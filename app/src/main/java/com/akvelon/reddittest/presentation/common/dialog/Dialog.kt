package com.akvelon.reddittest.presentation.common.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.akvelon.reddittest.R
import com.akvelon.reddittest.presentation.common.component.Headline
import com.akvelon.reddittest.presentation.common.component.TextUpperCase

@Preview
@Composable
fun Dialog(
    onCancelClick: () -> Unit = { },
    onOkClick: () -> Unit = { },
    isCancelButtonVisible: Boolean = true,
    title: String? = null,
    isShow: Boolean = false,
    properties: DialogProperties = DialogProperties(),
    okButtonColors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
    @StringRes cancelButtonTextRes: Int = R.string.cancel,
    @StringRes okButtonTextRes: Int = R.string.ok,
    content: @Composable () -> Unit = {}
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = onCancelClick,
            properties = properties,
            title = if (title != null) {
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Headline(
                            text = title,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            } else null,
            text = content,
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (isCancelButtonVisible) {
                        Button(
                            onClick = onCancelClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                        ) {
                            TextUpperCase(
                                text = stringResource(id = cancelButtonTextRes),
                                color = MaterialTheme.colors.primary
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    } else {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }
                    Button(
                        onClick = onOkClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        colors = okButtonColors
                    ) {
                        TextUpperCase(text = stringResource(id = okButtonTextRes))
                    }
                }
            }
        )
    }
}
