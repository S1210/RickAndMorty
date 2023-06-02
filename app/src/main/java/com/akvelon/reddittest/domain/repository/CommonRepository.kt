package com.akvelon.reddittest.domain.repository

import com.akvelon.reddittest.data.local.entity.RoomModel


interface CommonRepository<T : RoomModel> {

    suspend fun insertItem(item: T): Long

    suspend fun insertItems(items: List<T>)

    suspend fun replaceItem(item: T): Long

    suspend fun replaceItems(items: List<T>)

    suspend fun updateItem(item: T)

    suspend fun updateItems(items: List<T>)

    suspend fun upsertItem(item: T): Long

    suspend fun upsertItems(items: List<T>)

    suspend fun deleteItem(item: T)

    suspend fun deleteItems(items: List<T>)

    suspend fun deleteAll()

}