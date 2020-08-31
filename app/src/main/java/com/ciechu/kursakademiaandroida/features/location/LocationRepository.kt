package com.ciechu.kursakademiaandroida.features.location

import com.ciechu.kursakademiaandroida.features.location.domain.model.Location


interface LocationRepository {
    suspend fun getLocation(): List<Location>
}