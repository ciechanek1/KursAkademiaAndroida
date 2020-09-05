package com.ciechu.kursakademiaandroida.features.episodes.di

import com.ciechu.kursakademiaandroida.features.episodes.EpisodeRepository
import com.ciechu.kursakademiaandroida.features.episodes.data.repository.EpisodeRepositoryImpl
import com.ciechu.kursakademiaandroida.features.episodes.domain.GetEpisodesUseCase
import com.ciechu.kursakademiaandroida.features.episodes.presentation.EpisodeAdapter
import com.ciechu.kursakademiaandroida.features.episodes.presentation.EpisodeFragment
import com.ciechu.kursakademiaandroida.features.episodes.presentation.EpisodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val episodeModule = module {
    //data
    factory<EpisodeRepository> { EpisodeRepositoryImpl(get(), get(), get()) }

    //domain
    factory { GetEpisodesUseCase(get()) }

    //presentation
    viewModel { EpisodeViewModel(get()) }
    factory { EpisodeFragment() }
    factory { EpisodeAdapter() }
}