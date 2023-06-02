package com.akvelon.reddittest.presentation.main

import com.akvelon.reddittest.domain.repository.CharacterRepository
import com.akvelon.reddittest.extensions.launchWithHandle
import com.akvelon.reddittest.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        characterRepository.getCharactersFlow().onEach {
            _state.update { state ->
                state.copy(
                    characters = it,
                    haveCharacters = it.isNotEmpty()
                )
            }
            closeRefreshing()
        }.onStart { showRefreshing() }.launchWithHandle(this)
    }

    override suspend fun doRefresh() {
        super.doRefresh()
        characterRepository.saveCharacters(characterRepository.loadData())
    }

}