package com.ciechu.kursakademiaandroida.features.episodes.presentation.model

import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode

data class EpisodeDisplayable (
    val airDate: String,
    val characters: List<String>,
    val code: String,
    val id: Int,
    val name: String,
    val url: String
) {

    constructor(episode: Episode): this (
        id = episode.id,
        name = episode.name,
        airDate = episode.airDate,
        code = episode.episode,
        characters = episode.characters,
        url = episode.url
    )
}