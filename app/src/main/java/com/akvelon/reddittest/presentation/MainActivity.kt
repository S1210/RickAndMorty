package com.akvelon.reddittest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akvelon.reddittest.presentation.common.component.ViewModelScreen
import com.akvelon.reddittest.presentation.main.MainScreen
import com.akvelon.reddittest.ui.theme.RedditTestTheme
import com.akvelon.reddittest.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RedditTestTheme {
                val viewModel = hiltViewModel<SharedViewModel>()
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    ViewModelScreen(viewModel = viewModel, navController = navController) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.MainScreen.route
                        ) {
                            composable(route = Screen.MainScreen.route) {
                                MainScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}