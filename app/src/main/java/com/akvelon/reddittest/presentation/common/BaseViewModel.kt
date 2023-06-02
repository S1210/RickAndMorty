package com.akvelon.reddittest.presentation.common

import android.content.Intent
import androidx.annotation.CallSuper
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.akvelon.reddittest.R
import com.akvelon.reddittest.extensions.launchIO
import com.akvelon.reddittest.presentation.common.dialog.error.ErrorState
import com.akvelon.reddittest.presentation.common.dialog.progress_bar.ProgressDialogState
import com.akvelon.reddittest.presentation.navigation.NavigationState
import com.akvelon.reddittest.util.UiText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    private val _progressDialogFlow = MutableStateFlow(ProgressDialogState())
    val progressDialogFlow = _progressDialogFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow(ErrorState())
    val errorFlow = _errorFlow.asStateFlow()

    private val _navigation = Channel<NavigationState>()
    val navigation = _navigation.receiveAsFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing

    internal val exceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            errorEvents(throwable)
        }

    @CallSuper
    open fun errorEvents(throwable: Throwable, isShowError: Boolean = true) {
        if (isShowError) {
            when (throwable) {
                is UnknownHostException -> {
                    showErrorDialog(
                        titleRes = R.string.error,
                        messageRes = R.string.connection_error
                    )
                }
                else -> {
                    showErrorDialog(
                        titleRes = R.string.error,
                        message = throwable.localizedMessage
                    )
                }
            }
        }
        closeProgressDialog(_progressDialogFlow.value.tag)
        closeRefreshing()
    }

    protected fun getUiText(@StringRes stringRes: Int, args: List<Any> = emptyList()) =
        UiText.StringResource(stringRes, args)

    protected fun getUiText(text: String) = UiText.DynamicString(text)

    fun showProgressDialog(text: String = "", tag: String = "") {
        _progressDialogFlow.value = ProgressDialogState(
            isShow = true,
            text = getUiText(text),
            tag = tag
        )
    }

    fun showProgressDialog(@StringRes textRes: Int, tag: String = "") {
        _progressDialogFlow.value =
            ProgressDialogState(isShow = true, text = getUiText(textRes), tag = tag)
    }

    fun closeProgressDialog(tag: String = "") {
        if (_progressDialogFlow.value.tag == tag) _progressDialogFlow.value = ProgressDialogState()
    }

    fun refresh() = launchIO {
        showRefreshing()
        doRefresh()
        closeRefreshing()
    }

    open suspend fun doRefresh() {}

    fun showRefreshing() {
        _isRefreshing.value = true
    }

    fun closeRefreshing() {
        _isRefreshing.value = false
    }

    fun showErrorDialog(title: String = "", message: String?) {
        _errorFlow.value = ErrorState(
            isShow = true,
            title = getUiText(title),
            message = message?.let { getUiText(it) } ?: getUiText(R.string.unknown_error)
        )
    }

    fun showErrorDialog(title: String = "", @StringRes messageRes: Int) {
        _errorFlow.value = ErrorState(
            isShow = true,
            title = getUiText(title),
            message = getUiText(messageRes)
        )
    }

    fun showErrorDialog(@StringRes titleRes: Int, @StringRes messageRes: Int) {
        _errorFlow.value = ErrorState(
            isShow = true,
            title = getUiText(titleRes),
            message = getUiText(messageRes)
        )
    }

    fun showErrorDialog(@StringRes titleRes: Int, message: String?) {
        _errorFlow.value = ErrorState(
            isShow = true,
            title = getUiText(titleRes),
            message = message?.let { getUiText(it) } ?: getUiText(R.string.unknown_error)
        )
    }

    fun closeErrorDialog() {
        _errorFlow.value = ErrorState()
    }

    suspend fun popBackStack(isParent: Boolean = false) {
        _navigation.send(NavigationState.PopBackStack(isParent))
    }

    suspend fun popBackStackNavigate(route: String, isParent: Boolean = false) {
        _navigation.send(NavigationState.PopBackStackNavigate(route, isParent))
    }

    suspend fun popBackStackRoute(
        popBackRoute: String,
        inclusive: Boolean = false,
        navigateRoute: String? = null,
        isParent: Boolean = false
    ) {
        _navigation.send(
            NavigationState.PopBackStackRoute(
                popBackRoute = popBackRoute,
                inclusive = inclusive,
                navigate = navigateRoute?.let { NavigationState.Navigate(it) },
                isParent = isParent
            )
        )
    }

    suspend fun navigate(route: String, isParent: Boolean = false) {
        _navigation.send(NavigationState.Navigate(route, isParent))
    }

    suspend fun startActivity(intent: Intent) {
        _navigation.send(NavigationState.StartActivity(intent))
    }

}