package com.ciechu.kursakademiaandroida.features.location.data.repository

import com.ciechu.kursakademiaandroida.core.api.RickAndMortyApi
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.features.location.LocationRepository
import com.ciechu.kursakademiaandroida.features.location.data.local.LocationDao
import com.ciechu.kursakademiaandroida.features.location.data.local.model.LocationCached
import com.ciechu.kursakademiaandroida.features.location.domain.model.Location

class LocationRepositoryImpl(
    private val rickAndMortyApi: RickAndMortyApi,
    private val locationDao: LocationDao,
    private val networkStateProvider: NetworkStateProvider
) : LocationRepository {

    override suspend fun getLocation(): List<Location> {
        return if (networkStateProvider.isNetworkAvailable()) {
            getLocationsFromRemote()
                .also { saveLocationsToLocal(it) }
        } else {
            getLocationFromLocal()
        }
    }

    private suspend fun getLocationsFromRemote(): List<Location> {
        return rickAndMortyApi.getLocations()
            .results
            .map { it.toLocation() }
            .also { saveLocationsToLocal(it) }
    }

    private suspend fun saveLocationsToLocal(episodes: List<Location>) {
        episodes.map { LocationCached(it) }
            .toTypedArray()
            .let { locationDao.saveLocations(*it) }
    }

    private suspend fun getLocationFromLocal() =
        locationDao.getLocations()
            .map { it.toLocation() }

}