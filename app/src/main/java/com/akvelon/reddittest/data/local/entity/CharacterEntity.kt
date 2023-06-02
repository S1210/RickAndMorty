package com.akvelon.reddittest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akvelon.reddittest.data.local.TableNames.CHARACTERS

@Entity(tableName = CHARACTERS)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0,
    val name: String = "",
    val image: String = "",
    val gender: String = "",
    val status: String = "",
) : RoomModel()