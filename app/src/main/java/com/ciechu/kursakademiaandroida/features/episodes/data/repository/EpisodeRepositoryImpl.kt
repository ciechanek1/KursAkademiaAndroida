package com.ciechu.kursakademiaandroida.features.episodes.data.repository

import com.ciechu.kursakademiaandroida.core.api.RickAndMortyApi
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.features.episodes.EpisodeRepository
import com.ciechu.kursakademiaandroida.features.episodes.data.local.EpisodeDao
import com.ciechu.kursakademiaandroida.features.episodes.data.local.model.EpisodeCashed
import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode

class EpisodeRepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val episodeDao: EpisodeDao,
    private val networkStateProvider: NetworkStateProvider
) : EpisodeRepository {

    override suspend fun getEpisodes(): List<Episode> {
        return if (networkStateProvider.isNetworkAvailable()) {
            getEpisodesFromRemote()
                .also { saveEpisodesToLocal(it) }
        } else {
            getEpisodesFromLocal()
        }
    }

    private suspend fun getEpisodesFromRemote(): List<Episode> {
        return rickAndMortyApi.getEpisodes()
            .results
            .map { it.toEpisode() }
    }

    private suspend fun saveEpisodesToLocal(episode: List<Episode>) {
        episode.map { EpisodeCashed(it) }
            .toTypedArray()
            .let { episodeDao.saveEpisodes(*it) }
    }

    private suspend fun getEpisodesFromLocal(): List<Episode> {
        return episodeDao.getEpisodes()
            .map { it.toEpisode() }
    }

}