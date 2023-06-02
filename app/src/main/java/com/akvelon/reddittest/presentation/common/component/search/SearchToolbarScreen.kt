package com.akvelon.reddittest.presentation.common.component.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.akvelon.reddittest.R
import com.akvelon.reddittest.extensions.collectLifecycle
import com.akvelon.reddittest.presentation.common.component.ToolbarScreen

@Composable
fun <V : SearchViewModel> SearchToolbarScreen(
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
    searchState: SearchState = viewModel.searchState.collectLifecycle(),
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable @UiComposable (PaddingValues) -> Unit
) {
    ToolbarScreen(
        modifier = modifier,
        viewModel = viewModel,
        navController = navController,
        parentNavController = parentNavController,
        title = title,
        titleExtraContent = titleExtraContent,
        isShowIcon = isShowIcon,
        icon = icon,
        iconClick = iconClick,
        actions = actions,
        searchState = searchState,
        onQueryChange = viewModel::changeQuery,
        onStateChange = viewModel::changeState,
        onClearClick = { viewModel.changeQuery("") },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}
