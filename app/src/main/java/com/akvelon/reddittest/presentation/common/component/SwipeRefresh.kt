package com.akvelon.reddittest.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeRefresh(
    modifier: Modifier = Modifier,
    state: PullRefreshState,
    isRefreshing: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(state)
            .then(modifier)
    ) {
        content()
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colors.primary,
            scale = true
        )
    }
}
