package com.akvelon.reddittest.extensions

import androidx.navigation.NavController

fun NavController.navigateIfPossible(route: String) {
    tryCatch { navigate(route) }
}
