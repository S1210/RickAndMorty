package com.akvelon.reddittest.presentation.main

import androidx.compose.runtime.Stable

data class MainState(
    @Stable
    val characters: List<com.akvelon.reddittest.domain.model.Character> = emptyList(),
    val haveCharacters: Boolean = false
)
