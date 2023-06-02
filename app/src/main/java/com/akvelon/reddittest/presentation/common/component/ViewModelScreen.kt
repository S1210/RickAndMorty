package com.akvelon.reddittest.presentation.common.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.UiComposable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.akvelon.reddittest.extensions.collectLifecycle
import com.akvelon.reddittest.extensions.navigateIfPossible
import com.akvelon.reddittest.presentation.common.BaseViewModel
import com.akvelon.reddittest.presentation.common.dialog.error.ErrorDialog
import com.akvelon.reddittest.presentation.common.dialog.error.ErrorState
import com.akvelon.reddittest.presentation.common.dialog.progress_bar.ProgressBarDialog
import com.akvelon.reddittest.presentation.common.dialog.progress_bar.ProgressDialogState
import com.akvelon.reddittest.presentation.navigation.NavigationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun <V : BaseViewModel> ViewModelScreen(
    viewModel: V,
    navController: NavController,
    parentNavController: NavController? = null,
    progressDialogState: ProgressDialogState = viewModel.progressDialogFlow.collectLifecycle(),
    errorState: ErrorState = viewModel.errorFlow.collectLifecycle(),
    content: @Composable @UiComposable () -> Unit
) {
    val context = LocalContext.current
    ProgressBarDialog(state = progressDialogState)
    ErrorDialog(
        onOkClick = { viewModel.closeErrorDialog() },
        state = errorState
    )
    LaunchedEffect(key1 = Unit) {
        launch(Dispatchers.Main) {
            viewModel.navigation.collect {
                when (it) {
                    is NavigationState.PopBackStack -> {
                        if (it.isParent && parentNavController != null) parentNavController.popBackStack()
                        else navController.popBackStack()
                    }
                    is NavigationState.PopBackStackNavigate -> {
                        if (it.isParent && parentNavController != null) {
                            parentNavController.popBackStack()
                            parentNavController.navigateIfPossible(it.route)
                        } else {
                            navController.popBackStack()
                            navController.navigateIfPossible(it.route)
                        }
                    }
                    is NavigationState.PopBackStackRoute -> {
                        if (it.isParent && parentNavController != null) {
                            parentNavController.popBackStack(it.popBackRoute, it.inclusive)
                            it.navigate?.let { navigate ->
                                parentNavController.navigateIfPossible(navigate.route)
                            }
                        } else {
                            navController.popBackStack(it.popBackRoute, it.inclusive)
                            it.navigate?.let { navigate ->
                                navController.navigateIfPossible(navigate.route)
                            }
                        }
                    }
                    is NavigationState.Navigate -> {
                        if (it.isParent && parentNavController != null) {
                            parentNavController.navigateIfPossible(it.route)
                        } else {
                            navController.navigateIfPossible(it.route)
                        }
                    }
                    is NavigationState.StartActivity -> context.startActivity(it.intent)
                }
            }
        }
    }
    content()
}
