package com.ciechu.kursakademiaandroida.mock

import com.ciechu.kursakademiaandroida.core.api.model.EpisodeRemote
import com.ciechu.kursakademiaandroida.core.api.model.EpisodesResponse
import com.ciechu.kursakademiaandroida.core.api.model.ResponseInfo
import com.ciechu.kursakademiaandroida.features.episodes.data.local.model.EpisodeCashed
import org.jetbrains.annotations.TestOnly

@TestOnly
fun ResponseInfo.Companion.mock() = ResponseInfo(
    count = 10,
    pages = 3,
    next = "next page url",
    prev = "previous page url"
)

@TestOnly
fun EpisodeRemote.Companion.mock() = EpisodeRemote(
    id = 1,
    name = "episode name",
    airDate = "episode air date",
    code = "episode code",
    characters = emptyList(),
    url = "episode url",
    created = "example data"
)

@TestOnly
fun EpisodesResponse.Companion.mock() = EpisodesResponse(
    info = ResponseInfo.mock(),
    results = listOf(
        EpisodeRemote.mock(),
        EpisodeRemote.mock(),
        EpisodeRemote.mock()
    )
)

@TestOnly
fun EpisodeCashed.Companion.mock() = EpisodeCashed(
    id = 1,
    name = "episode name",
    airDate = "episode air date",
    code = "episode code",
    characters = emptyList(),
    url = "episode url"
)
