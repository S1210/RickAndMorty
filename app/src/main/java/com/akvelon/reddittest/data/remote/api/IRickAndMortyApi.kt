package com.akvelon.reddittest.data.remote.api

import com.akvelon.reddittest.data.remote.dto.CharacterDto
import retrofit2.Response
import retrofit2.http.GET

interface IRickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(): Response<CharacterDto>

}