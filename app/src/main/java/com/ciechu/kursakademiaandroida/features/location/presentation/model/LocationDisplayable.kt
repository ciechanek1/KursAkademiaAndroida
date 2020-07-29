package com.ciechu.kursakademiaandroida.features.location.presentation.model

import com.ciechu.kursakademiaandroida.features.location.domain.model.Location

data class LocationDisplayable(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
) {
    constructor(location: Location): this (
        created = location.created,
        dimension = location.dimension,
        id = location.id,
        name = location.name,
        residents = location.residents,
        type = location.type,
        url = location.url
    )
}