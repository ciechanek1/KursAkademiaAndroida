package com.ciechu.kursakademiaandroida.features.location.domain

import android.location.Location
import com.ciechu.kursakademiaandroida.core.base.UseCase
import com.ciechu.kursakademiaandroida.features.location.LocationRepository

class GetLocationUseCase(private val locationRepository: LocationRepository): UseCase<List<Location>, Unit>() {

    override suspend fun action(params: Unit) = locationRepository.getLocation()
}