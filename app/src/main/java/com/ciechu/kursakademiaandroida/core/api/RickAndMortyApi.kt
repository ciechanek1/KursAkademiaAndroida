package com.ciechu.kursakademiaandroida.core.api

import com.ciechu.kursakademiaandroida.core.api.model.EpisodesResponse
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("episodes")
    suspend fun getEpisodes(): EpisodesResponse
}