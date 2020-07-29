package com.ciechu.kursakademiaandroida.features.data.remote.model

import com.ciechu.kursakademiaandroida.features.character.domain.model.Origin
import com.google.gson.annotations.SerializedName

data class OriginRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
){
    fun toOrigin() = Origin(
        name = name,
        url = url
    )
}