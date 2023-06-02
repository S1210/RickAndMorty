package com.akvelon.reddittest.presentation.common.component.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.akvelon.reddittest.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Search(
    state: SearchState,
    onClearClick: () -> Unit,
    onQueryChange: (query: String) -> Unit,
    onStateChange: (isOpen: Boolean) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val search = stringResource(id = R.string.search)
    LaunchedEffect(key1 = state.isOpen) {
        if (state.isOpen) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }
    AnimatedVisibility(visible = state.isOpen) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.background)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = search
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .focusRequester(focusRequester),
                        value = state.query,
                        onValueChange = onQueryChange,
                        singleLine = true,
                        textStyle = TextStyle.Default.copy(color = LocalContentColor.current),
                        cursorBrush = SolidColor(LocalContentColor.current),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() })
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = onClearClick) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.clear)
                        )
                    }
                }
            }
            IconButton(onClick = {
                onStateChange(false)
                keyboardController?.hide()
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.close),
                    tint = Color.White
                )
            }
        }
    }
    AnimatedVisibility(visible = !state.isOpen) {
        IconButton(onClick = {
            onStateChange(true)
        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = search,
                tint = Color.White
            )
        }
    }
}
