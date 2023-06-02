package com.akvelon.reddittest.presentation.util

sealed class Screen(val route: String) {

    object MainScreen : Screen("pizzas_screen")

}
