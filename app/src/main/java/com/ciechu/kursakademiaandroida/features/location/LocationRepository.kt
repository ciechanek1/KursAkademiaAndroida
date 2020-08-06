package com.ciechu.kursakademiaandroida.features.location

import android.location.Location

interface LocationRepository {
    suspend fun getLocation(): List<Location>
}