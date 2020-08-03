package com.ciechu.kursakademiaandroida.features.character.presentation.model

import com.ciechu.kursakademiaandroida.features.character.domain.model.Character
import com.ciechu.kursakademiaandroida.features.data.remote.model.LocationRemote
import com.ciechu.kursakademiaandroida.features.data.remote.model.OriginRemote

data class CharacterDisplayable (
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationRemote,
    val name: String,
    val origin: OriginRemote,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    constructor(character: Character): this (
        created = character.created,
        episode = character.episode,
        gender = character.gender,
        id = character.id,
        image = character.image,
        location = character.location,
        name = character.name,
        origin = character.origin,
        species = character.species,
        status = character.status,
        type = character.type,
        url = character.url
    )
}