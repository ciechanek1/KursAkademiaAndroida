package com.ciechu.kursakademiaandroida.features.episodes.domain

import com.ciechu.kursakademiaandroida.features.episodes.EpisodeRepository
import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode
import kotlinx.coroutines.*

class GetEpisodesUseCase(private val episodeRepository: EpisodeRepository) {

    operator fun invoke(scope: CoroutineScope,
    executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
    onResults: (Result<List<Episode>>) -> Unit = {}) {
        scope.launch {
           val results = withContext(executionDispatcher) {
               runCatching { episodeRepository.getEpisodes() }
           }
            results.onSuccess {  }
            results.onFailure {  }
            onResults(results)
        }
    }
}