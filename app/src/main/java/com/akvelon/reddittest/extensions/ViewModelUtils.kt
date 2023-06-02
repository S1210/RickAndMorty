package com.akvelon.reddittest.extensions

import androidx.lifecycle.viewModelScope
import com.akvelon.reddittest.presentation.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun BaseViewModel.launch(isShowError: Boolean = true, lambda: suspend () -> Unit) =
    viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
        sendReport(isShowError, lambda)
    }

fun BaseViewModel.launchIO(isShowError: Boolean = true, lambda: suspend () -> Unit) =
    viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
        sendReport(isShowError, lambda)
    }

fun BaseViewModel.launchDefault(isShowError: Boolean = true, lambda: suspend () -> Unit) =
    viewModelScope.launch(Dispatchers.Default + exceptionHandler) {
        sendReport(isShowError, lambda)
    }

private suspend fun BaseViewModel.sendReport(
    isShowError: Boolean,
    lambda: suspend () -> Unit
) {
    try {
        lambda()
    } catch (e: Exception) {
        if (isShowError) errorEvents(e)
    }
}





