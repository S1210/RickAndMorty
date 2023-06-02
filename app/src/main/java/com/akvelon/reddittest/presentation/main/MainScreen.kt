package com.akvelon.reddittest.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.akvelon.reddittest.R
import com.akvelon.reddittest.extensions.collectLifecycle
import com.akvelon.reddittest.presentation.common.component.SwipeRefresh
import com.akvelon.reddittest.presentation.common.component.ToolbarScreen
import com.akvelon.reddittest.presentation.main.component.CharacterItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    state: MainState = viewModel.state.collectLifecycle(),
    isRefreshing: Boolean = viewModel.isRefreshing.collectLifecycle()
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = viewModel::refresh
    )
    ToolbarScreen(
        viewModel = viewModel,
        navController = navController,
        isShowIcon = false
    ) {
        SwipeRefresh(state = pullRefreshState, isRefreshing = isRefreshing) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.characters) {
                        CharacterItem(character = it)
                    }
                }
                if (!state.haveCharacters && !isRefreshing) {
                    Text(text = stringResource(id = R.string.no_characters_message))
                }
            }
        }
    }
}