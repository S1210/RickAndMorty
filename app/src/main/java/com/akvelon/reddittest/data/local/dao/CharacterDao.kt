package com.akvelon.reddittest.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.akvelon.reddittest.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CharacterDao : CommonDao<CharacterEntity> {

    @Query("SELECT * FROM characters")
    abstract fun getCharactersFlow(): Flow<List<CharacterEntity>>

    @Query("SELECT EXISTS(SELECT * FROM characters)")
    abstract suspend fun haveCharacters(): Boolean

    @Query("DELETE FROM characters")
    abstract override suspend fun deleteAll()

}