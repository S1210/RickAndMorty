package com.akvelon.reddittest.util.mapper

import com.akvelon.reddittest.data.local.entity.CharacterEntity
import com.akvelon.reddittest.domain.model.Character
import com.akvelon.reddittest.data.remote.dto.Result

fun Result.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id.toLong(),
        name = name,
        image = image,
        gender = gender,
        status = status
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        image = image,
        gender = gender,
        status = status
    )
}