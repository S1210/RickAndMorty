package com.akvelon.reddittest.data.remote.api

import com.akvelon.reddittest.data.remote.dto.CharacterDto
import com.akvelon.reddittest.extensions.withIO
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(
    private val iRickAndMortyApi: IRickAndMortyApi
) {

    suspend fun getCharacters(): Response<CharacterDto> = withIO {
        iRickAndMortyApi.getCharacters()
    }

}