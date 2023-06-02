package com.akvelon.reddittest.data.repository

import com.akvelon.reddittest.data.local.dao.CommonDao
import com.akvelon.reddittest.data.local.entity.RoomModel

abstract class CommonRepositoryImpl<T : RoomModel, D : CommonDao<T>>(
    private val dao: D
) {

    suspend fun insertItem(item: T) = dao.insertItem(item)

    suspend fun insertItems(items: List<T>) = dao.insertItems(items)

    suspend fun replaceItem(item: T) = dao.replaceItem(item)

    suspend fun replaceItems(items: List<T>) = dao.replaceItems(items)

    suspend fun updateItem(item: T) = dao.updateItem(item)

    suspend fun updateItems(items: List<T>) = dao.updateItems(items)

    suspend fun upsertItem(item: T) = dao.upsertItem(item)

    suspend fun upsertItems(items: List<T>) = dao.upsertItems(items)

    suspend fun deleteItem(item: T) = dao.deleteItem(item)

    suspend fun deleteItems(items: List<T>) = dao.deleteItems(items)

    suspend fun deleteAll() = dao.deleteAll()

}