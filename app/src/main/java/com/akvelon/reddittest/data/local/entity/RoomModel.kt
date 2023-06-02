package com.akvelon.reddittest.data.local.entity

import androidx.room.PrimaryKey

abstract class RoomModel(
    @PrimaryKey(autoGenerate = true)
    open var id: Long = 0
)