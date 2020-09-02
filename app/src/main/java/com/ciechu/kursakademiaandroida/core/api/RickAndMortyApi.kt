package com.ciechu.kursakademiaandroida.core.api

import com.ciechu.kursakademiaandroida.core.api.model.CharacterResponse
import com.ciechu.kursakademiaandroida.core.api.model.EpisodesResponse
import com.ciechu.kursakademiaandroida.core.api.model.LocationResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse

    @GET("location")
    suspend fun getLocations(): LocationResponse

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}