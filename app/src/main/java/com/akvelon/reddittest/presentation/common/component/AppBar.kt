package com.akvelon.reddittest.presentation.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Toolbar(
    isSearchOpen: Boolean = false,
    title: String,
    titleExtraContent: @Composable @UiComposable (() -> Unit)? = null,
    onBackPressed: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    isShowIcon: Boolean = true,
    icon: ImageVector = Icons.Filled.ArrowBack
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!isSearchOpen) {
                    Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    titleExtraContent?.let {
                        Spacer(modifier = Modifier.width(8.dp))
                        it()
                    }
                }
            }
        },
        actions = actions,
        navigationIcon = if (isShowIcon) {
            {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Back icon"
                    )
                }
            }
        } else null,
        backgroundColor = backgroundColor
    )
}
