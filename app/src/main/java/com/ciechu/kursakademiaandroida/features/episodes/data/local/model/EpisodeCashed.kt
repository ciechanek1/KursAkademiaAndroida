package com.ciechu.kursakademiaandroida.features.episodes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode


@Entity
data class EpisodeCashed(
    @PrimaryKey
    val id: Int,
    val airDate: String,
    val characters: List<String>,
    val code: String,
    val name: String,
    val url: String
) {

    constructor(episode: Episode): this (
        episode.id,
        episode.airDate,
        episode.characters,
        episode.code,
        episode.name,
        episode.url
    )

    companion object

    fun toEpisode() = Episode(
        id = id,
        name = name,
        airDate = airDate,
        code = code,
        characters = characters,
        url = url
    )
}