package com.ciechu.kursakademiaandroida.features.data.remote.model

import com.ciechu.kursakademiaandroida.features.location.domain.model.Location
import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("created") val created: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("residents") val residents: List<String>,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
){
    fun toLocation() = Location(
        created = created,
        dimension = dimension,
        id = id,
        name = name,
        residents = residents,
        type = type,
        url = url
    )
}