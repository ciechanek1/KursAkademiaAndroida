package com.ciechu.kursakademiaandroida.features.character.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character
import com.ciechu.kursakademiaandroida.features.character.domain.model.CharacterLocation
import com.ciechu.kursakademiaandroida.features.character.domain.model.CharacterOrigin

@Entity
data class CharacterCached(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded
    val characterOriginCached: CharacterOriginCached,
    @Embedded
    val characterLocationCached: CharacterLocationCached,
    val image: String,
    val episode: List<String>,
    val url: String
) {
    constructor(character: Character) : this(
        character.id,
        character.name,
        character.status,
        character.species,
        character.type,
        character.gender,
        CharacterOriginCached(character.characterOrigin),
        CharacterLocationCached(character.characterLocation),
        character.image,
        character.episode,
        character.url
    )

    companion object

    fun toCharacter() = Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        characterOrigin = characterOriginCached.toCharacterOrigin(),
        characterLocation = characterLocationCached.toCharacterLocation(),
        image = image,
        episode = episode,
        url = url
    )
}

data class CharacterOriginCached(
    val originName: String,
    val originUrl: String
) {
    constructor(characterOrigin: CharacterOrigin) : this(
        characterOrigin.name,
        characterOrigin.url
    )

    companion object

    fun toCharacterOrigin() = CharacterOrigin(
        name = originName,
        url = originUrl
    )
}

data class CharacterLocationCached(
    val locationName: String,
    val locationUrl: String
) {
    constructor(characterLocation: CharacterLocation) : this(
        characterLocation.name,
        characterLocation.url
    )

    companion object

    fun toCharacterLocation() = CharacterLocation(
        name = locationName,
        url = locationUrl
    )
}