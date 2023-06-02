package com.akvelon.reddittest.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.akvelon.reddittest.controller.AppController
import com.akvelon.reddittest.presentation.common.BaseViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> Flow<T>.launchWithHandle(
    viewModel: BaseViewModel,
    onStartEvent: suspend FlowCollector<T>.() -> Unit = {}
) = onStart { onStartEvent() }.launchWithHandle(viewModel)

private fun <T> Flow<T>.launchWithHandle(viewModel: BaseViewModel) = viewModel.launchIO {
    catch { viewModel.errorEvents(it) }.flowOn(Dispatchers.IO).collect()
}

fun <T> Flow<T>.onStartHandle(
    appController: AppController,
    onStartEvent: suspend FlowCollector<T>.() -> Unit = {}
) = onStart {
    try {
        onStartEvent()
    } catch (e: Exception) {
        if (e !is CancellationException) appController.sendError(e)
    }
}

@Composable
fun <T> Flow<T>.collectLifecycle(
    defValue: T,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext
) = collectAsStateWithLifecycle(
    initialValue = defValue,
    lifecycleOwner = lifecycleOwner,
    minActiveState = minActiveState,
    context = context
).value

@Composable
fun <T> StateFlow<T>.collectLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext
) = collectAsStateWithLifecycle(
    lifecycleOwner = lifecycleOwner,
    minActiveState = minActiveState,
    context = context
).value