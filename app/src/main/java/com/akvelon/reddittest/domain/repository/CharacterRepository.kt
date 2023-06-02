package com.akvelon.reddittest.domain.repository

import com.akvelon.reddittest.data.local.entity.CharacterEntity
import com.akvelon.reddittest.data.remote.dto.CharacterDto
import kotlinx.coroutines.flow.Flow

interface CharacterRepository : CommonRepository<CharacterEntity> {

    fun getCharactersFlow(): Flow<List<com.akvelon.reddittest.domain.model.Character>>

    suspend fun haveCharacters(): Boolean

    suspend fun saveCharacters(characterDto: CharacterDto?)

    suspend fun loadData(): CharacterDto?

}