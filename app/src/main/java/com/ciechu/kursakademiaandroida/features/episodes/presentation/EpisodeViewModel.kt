package com.ciechu.kursakademiaandroida.features.episodes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ciechu.kursakademiaandroida.core.base.BaseViewModel
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapper
import com.ciechu.kursakademiaandroida.features.episodes.domain.GetEpisodesUseCase
import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode
import com.ciechu.kursakademiaandroida.features.episodes.presentation.model.EpisodeDisplayable

class EpisodeViewModel(private val episodesUseCase: GetEpisodesUseCase,
                       errorMapper: ErrorMapper
): BaseViewModel(errorMapper) {

    private val _episodes by lazy {
        MutableLiveData<List<Episode>>()
            .also { getEpisodes(it) }
    }

    val episodes: LiveData<List<EpisodeDisplayable>> by lazy {
        _episodes.map { episodes ->
            episodes.map { EpisodeDisplayable(it) }
        }
    }

    private fun getEpisodes(episodesLiveData: MutableLiveData<List<Episode>>) {
        setPendingState()
        episodesUseCase(
            params = Unit,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess { episodesLiveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }
}