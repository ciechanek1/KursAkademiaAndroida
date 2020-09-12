package com.ciechu.kursakademiaandroida.features.location.data.repository

import com.ciechu.kursakademiaandroida.core.api.RickAndMortyApi
import com.ciechu.kursakademiaandroida.core.api.model.LocationResponse
import com.ciechu.kursakademiaandroida.core.exeption.ErrorWrapper
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.features.location.LocationRepository
import com.ciechu.kursakademiaandroida.features.location.data.local.LocationDao
import com.ciechu.kursakademiaandroida.features.location.data.local.model.LocationCached
import com.ciechu.kursakademiaandroida.mock.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class LocationRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN locations request THEN fetch locations from API`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getLocations() } returns LocationResponse.mock()
        }
        val locationDao = mockk<LocationDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: LocationRepository =
            LocationRepositoryImpl(api, locationDao, networkStateProvider, errorWrapper)

        //when
        runBlocking { repository.getLocation() }

        //then
        coVerify { api.getLocations() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetch WHEN locations request THEN save locations to local database`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getLocations() } returns LocationResponse.mock()
        }
        val locationDao = mockk<LocationDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: LocationRepository =
            LocationRepositoryImpl(api, locationDao, networkStateProvider, errorWrapper)

        //when
        runBlocking { repository.getLocation() }

        //then
        coVerify { locationDao.saveLocations(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN locations request THEN fetch locations from local database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val locationDao = mockk<LocationDao> {
            coEvery { getLocations() } returns listOf(LocationCached.mock(), LocationCached.mock())
        }
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns false
        }
        val errorWrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: LocationRepository =
            LocationRepositoryImpl(api, locationDao, networkStateProvider, errorWrapper)

        //when
        runBlocking { repository.getLocation() }

        //then
        coVerify { locationDao.getLocations() }
    }
}