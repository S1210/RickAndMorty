package com.akvelon.reddittest.data.repository

import com.akvelon.reddittest.controller.AppController
import com.akvelon.reddittest.data.local.dao.CharacterDao
import com.akvelon.reddittest.data.local.entity.CharacterEntity
import com.akvelon.reddittest.data.remote.api.ApiManager
import com.akvelon.reddittest.data.remote.dto.CharacterDto
import com.akvelon.reddittest.domain.model.Character
import com.akvelon.reddittest.domain.repository.CharacterRepository
import com.akvelon.reddittest.extensions.onStartHandle
import com.akvelon.reddittest.extensions.tryCatch
import com.akvelon.reddittest.extensions.withIO
import com.akvelon.reddittest.util.mapper.toCharacter
import com.akvelon.reddittest.util.mapper.toCharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val apiManager: ApiManager,
    private val appController: AppController
) : CommonRepositoryImpl<CharacterEntity, CharacterDao>(characterDao), CharacterRepository {

    override fun getCharactersFlow(): Flow<List<Character>> {
        return characterDao.getCharactersFlow()
            .map { list -> list.map { it.toCharacter() } }
            .onStartHandle(appController) {
                if (!haveCharacters()) saveCharacters(loadData())
            }.flowOn(Dispatchers.IO)
    }

    override suspend fun haveCharacters(): Boolean = withIO {
        characterDao.haveCharacters()
    }

    override suspend fun saveCharacters(characterDto: CharacterDto?): Unit = withIO {
        characterDto?.let { list ->
            characterDao.upsertItems(list.results.map { it.toCharacterEntity() })
        }
    }

    override suspend fun loadData(): CharacterDto? = withIO {
        apiManager.getCharacters().run {
            if (isSuccessful) body() else null
        }
    }

}