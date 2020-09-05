package com.ciechu.kursakademiaandroida.core.di

import com.ciechu.kursakademiaandroida.features.episodes.di.episodeModule
import org.koin.core.module.Module

val featuresModule = listOf<Module>(
    episodeModule
)