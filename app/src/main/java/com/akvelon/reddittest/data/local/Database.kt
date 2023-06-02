package com.akvelon.reddittest.data.local

import androidx.room.RoomDatabase
import com.akvelon.reddittest.data.local.dao.CharacterDao
import com.akvelon.reddittest.data.local.entity.CharacterEntity

@androidx.room.Database(entities = [CharacterEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "room_db.db"
    }

    abstract fun characterDao(): CharacterDao

}