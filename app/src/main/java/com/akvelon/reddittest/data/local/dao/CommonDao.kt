package com.akvelon.reddittest.data.local.dao

import androidx.room.*
import com.akvelon.reddittest.data.local.entity.RoomModel

interface CommonDao<T : RoomModel> {

    @Insert
    suspend fun insertItem(item: T): Long

    @Transaction
    @Insert
    suspend fun insertItems(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceItem(item: T): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceItems(items: List<T>)

    @Update
    suspend fun updateItem(item: T)

    @Transaction
    @Update
    suspend fun updateItems(items: List<T>)

    @Upsert
    suspend fun upsertItem(item: T): Long

    @Transaction
    @Upsert
    suspend fun upsertItems(items: List<T>)

    @Delete
    suspend fun deleteItem(item: T)

    @Delete
    suspend fun deleteItems(items: List<T>)

    suspend fun deleteAll()

}