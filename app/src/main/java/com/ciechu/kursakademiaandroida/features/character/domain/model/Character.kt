package com.ciechu.kursakademiaandroida.features.character.domain.model

import com.ciechu.kursakademiaandroida.core.api.model.LocationRemote
import com.ciechu.kursakademiaandroida.core.api.model.OriginRemote

data class Character (
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
)