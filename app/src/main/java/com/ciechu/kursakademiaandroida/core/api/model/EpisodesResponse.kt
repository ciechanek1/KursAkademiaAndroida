package com.ciechu.kursakademiaandroida.core.api.model

import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("info") val info: ResponseInfo,
    @SerializedName("results") val results: List<EpisodeRemote>
){
    companion object

}