package com.ciechu.kursakademiaandroida.core.di

import com.ciechu.kursakademiaandroida.features.character.di.characterModule
import com.ciechu.kursakademiaandroida.features.episodes.di.episodeModule
import com.ciechu.kursakademiaandroida.features.location.di.locationModule

val featuresModule = listOf(
    episodeModule,
    locationModule,
    characterModule
)