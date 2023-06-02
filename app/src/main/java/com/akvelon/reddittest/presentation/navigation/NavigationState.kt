package com.akvelon.reddittest.presentation.navigation

import android.content.Intent

sealed interface NavigationState {

    data class PopBackStack(val isParent: Boolean = false) : NavigationState

    data class PopBackStackNavigate(
        val route: String,
        val isParent: Boolean = false
    ) : NavigationState

    data class PopBackStackRoute(
        val popBackRoute: String,
        val inclusive: Boolean = false,
        val navigate: Navigate? = null,
        val isParent: Boolean = false
    ) : NavigationState

    data class Navigate(val route: String, val isParent: Boolean = false) : NavigationState

    data class StartActivity(val intent: Intent) : NavigationState

}