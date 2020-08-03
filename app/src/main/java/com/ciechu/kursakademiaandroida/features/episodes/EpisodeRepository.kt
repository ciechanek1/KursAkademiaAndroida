package com.ciechu.kursakademiaandroida.features.episodes

import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodes(): List<Episode>
}