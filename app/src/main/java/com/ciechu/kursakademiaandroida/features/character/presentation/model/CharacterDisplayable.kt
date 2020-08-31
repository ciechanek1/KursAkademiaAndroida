package com.ciechu.kursakademiaandroida.features.character.presentation.model

import com.ciechu.kursakademiaandroida.features.character.domain.model.Character
import com.ciechu.kursakademiaandroida.features.character.domain.model.CharacterLocation
import com.ciechu.kursakademiaandroida.features.character.domain.model.CharacterOrigin

data class CharacterDisplayable (
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val characterLocation: CharacterLocationDisplayable,    val name: String,
    val characterOrigin: CharacterOriginDisplayable,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    constructor(character: Character): this (
        episode = character.episode,
        gender = character.gender,
        id = character.id,
        image = character.image,
        characterLocation = CharacterLocationDisplayable(character.characterLocation),
        name = character.name,
        characterOrigin = CharacterOriginDisplayable(character.characterOrigin),        species = character.species,
        status = character.status,
        type = character.type,
        url = character.url
    )
    data class CharacterOriginDisplayable(
        val name: String,
        val url: String
    ) {
        constructor(characterOrigin: CharacterOrigin) : this(
            name = characterOrigin.name,
            url = characterOrigin.url
        )
    }

    data class CharacterLocationDisplayable(
        val name: String,
        val url: String
    ) {
        constructor(characterLocation: CharacterLocation) : this(
            name = characterLocation.name,
            url = characterLocation.url
        )
    }
}