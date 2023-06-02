package com.akvelon.reddittest.presentation.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.akvelon.reddittest.R
import com.akvelon.reddittest.presentation.common.BaseViewModel
import com.akvelon.reddittest.presentation.common.component.search.Search
import com.akvelon.reddittest.presentation.common.component.search.SearchState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <V : BaseViewModel> ToolbarScreen(
    modifier: Modifier = Modifier,
    viewModel: V,
    navController: NavController,
    parentNavController: NavController? = null,
    title: String = stringResource(id = R.string.app_name),
    titleExtraContent: @Composable @UiComposable (() -> Unit)? = null,
    isShowIcon: Boolean = true,
    icon: ImageVector = Icons.Filled.ArrowBack,
    iconClick: () -> Unit = { navController.popBackStack() },
    actions: @Composable RowScope.() -> Unit = {},
    searchState: SearchState? = null,
    onQueryChange: (query: String) -> Unit = {},
    onStateChange: (isOpen: Boolean) -> Unit = {},
    onClearClick: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.background,
    toolbarBackgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable @UiComposable (PaddingValues) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    ViewModelScreen(
        viewModel = viewModel,
        navController = navController,
        parentNavController = parentNavController,
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                Toolbar(
                    isSearchOpen = searchState?.isOpen == true,
                    title = title,
                    titleExtraContent = titleExtraContent,
                    isShowIcon = isShowIcon,
                    icon = icon,
                    onBackPressed = {
                        keyboardController?.hide()
                        iconClick()
                    },
                    actions = {
                        searchState?.let {
                            Search(
                                state = searchState,
                                onQueryChange = onQueryChange,
                                onStateChange = onStateChange,
                                onClearClick = onClearClick
                            )
                        }
                        actions()
                    },
                    backgroundColor = toolbarBackgroundColor
                )
            },
            bottomBar = bottomBar,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition,
            isFloatingActionButtonDocked = isFloatingActionButtonDocked,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            content = content
        )
    }
}
