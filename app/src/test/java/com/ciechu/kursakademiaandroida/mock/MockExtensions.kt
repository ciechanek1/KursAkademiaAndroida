package com.ciechu.kursakademiaandroida.mock

import com.ciechu.kursakademiaandroida.core.api.model.*
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterCached
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterLocationCached
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterOriginCached
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character
import com.ciechu.kursakademiaandroida.features.character.domain.model.CharacterLocation
import com.ciechu.kursakademiaandroida.features.character.domain.model.CharacterOrigin
import com.ciechu.kursakademiaandroida.features.episodes.data.local.model.EpisodeCashed
import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode
import com.ciechu.kursakademiaandroida.features.location.data.local.model.LocationCached
import com.ciechu.kursakademiaandroida.features.location.domain.model.Location
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

@TestOnly
fun LocationRemote.Companion.mock() = LocationRemote(
    id = 1,
    name = "location name",
    type = "location type",
    dimension = "location dimensions",
    residents = emptyList(),
    url = "location url",
    created = "example data"
)

@TestOnly
fun LocationResponse.Companion.mock() = LocationResponse(
    info = ResponseInfo.mock(),
    results = listOf(
        LocationRemote.mock(),
        LocationRemote.mock(),
        LocationRemote.mock()
    )
)

@TestOnly
fun LocationCached.Companion.mock() = LocationCached(
    id = 1,
    name = "location name",
    type = "location type",
    dimension = "location dimensions",
    residents = emptyList(),
    url = "location url"
)

@TestOnly
fun CharacterOriginRemote.Companion.mock() = CharacterOriginRemote(
    name = "character origin name",
    url = "character origin url"
)

@TestOnly
fun CharacterLocationRemote.Companion.mock() = CharacterLocationRemote(
    name = "character location name",
    url = "character location url"
)

@TestOnly
fun CharacterRemote.Companion.mock() = CharacterRemote(
    id = 1,
    name = "character name",
    status = "character status",
    species = "character species",
    type = "character type",
    gender = "character gender",
    characterOriginRemote = CharacterOriginRemote.mock(),
    characterLocationRemote = CharacterLocationRemote.mock(),
    image = "character image",
    episode = emptyList(),
    url = "character url",
    created = "example data"
)

@TestOnly
fun CharacterResponse.Companion.mock() = CharacterResponse(
    info = ResponseInfo.mock(),
    results = listOf(
        CharacterRemote.mock(),
        CharacterRemote.mock(),
        CharacterRemote.mock()
    )
)

@TestOnly
fun CharacterOriginCached.Companion.mock() = CharacterOriginCached(
    originName = "character origin name",
    originUrl = "character origin url"
)

@TestOnly
fun CharacterLocationCached.Companion.mock() = CharacterLocationCached(
    locationName = "character location name",
    locationUrl = "character location url"
)

@TestOnly
fun CharacterCached.Companion.mock() = CharacterCached(
    id = 1,
    name = "character name",
    status = "character status",
    species = "character species",
    type = "character type",
    gender = "character gender",
    characterOriginCached = CharacterOriginCached.mock(),
    characterLocationCached = CharacterLocationCached.mock(),
    image = "character image",
    episode = emptyList(),
    url = "character url"
)

@TestOnly
fun Episode.Companion.mock() = Episode(
    id = 1,
    name = "episode name",
    airDate = "episode air date",
    code = "episode code",
    characters = emptyList(),
    url = "episode url"
)

@TestOnly
fun CharacterOrigin.Companion.mock() = CharacterOrigin(
    name = "character origin name",
    url = "character origin url"
)

@TestOnly
fun CharacterLocation.Companion.mock() = CharacterLocation(
    name = "character location name",
    url = "character location url"
)

@TestOnly
fun Character.Companion.mock() = Character(
    id = 1,
    name = "character name",
    status = "character status",
    species = "character species",
    type = "character type",
    gender = "character gender",
    characterOrigin = CharacterOrigin.mock(),
    characterLocation = CharacterLocation.mock(),
    image = "character image",
    episode = emptyList(),
    url = "character url"
)
@TestOnly
fun Location.Companion.mock() = Location(
    id = 1,
    name = "location name",
    type = "location type",
    dimension = "location dimensions",
    residents = emptyList(),
    url = "location url"
)