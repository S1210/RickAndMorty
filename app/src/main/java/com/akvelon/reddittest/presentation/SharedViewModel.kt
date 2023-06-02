package com.akvelon.reddittest.presentation

import com.akvelon.reddittest.controller.AppController
import com.akvelon.reddittest.extensions.launchWithHandle
import com.akvelon.reddittest.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    appController: AppController
) : BaseViewModel() {

    init {
        appController.errorHandler.onEach {
            errorEvents(it.throwable, it.isShowError)
        }.launchWithHandle(this)
        appController.appMessage.onEach {
            showErrorDialog(title = it.title, message = it.message)
        }.launchWithHandle(this)
    }

}