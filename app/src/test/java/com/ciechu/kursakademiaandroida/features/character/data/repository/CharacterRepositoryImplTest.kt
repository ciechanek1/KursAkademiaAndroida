package com.ciechu.kursakademiaandroida.features.character.data.repository

import com.ciechu.kursakademiaandroida.core.api.RickAndMortyApi
import com.ciechu.kursakademiaandroida.core.api.model.CharacterResponse
import com.ciechu.kursakademiaandroida.core.exeption.ErrorWrapper
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.features.character.data.local.CharacterDao
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterCached
import com.ciechu.kursakademiaandroida.features.character.domain.CharacterRepository
import com.ciechu.kursakademiaandroida.mock.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CharacterRepositoryImplTest {

    @Test
    fun `GIVEN network is connected WHEN character request THEM fetch episodes from API`(){
        //given
        val api = mockk<RickAndMortyApi>{
            coEvery { getCharacters() } returns CharacterResponse.mock()
        }
        val characterDao = mockk<CharacterDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider>{
            every { isNetworkAvailable() } returns true
        }

        val wrapper = mockk<ErrorWrapper>(relaxed = true)
        val  repository: CharacterRepository = CharacterRepositoryImpl(api, characterDao, networkStateProvider, wrapper)

        //when
        runBlocking { repository.getCharacters() }

        //then
        coVerify { api.getCharacters() }
    }

    @Test
    fun `GIVEN network is connected AND successful data fetch WHEN characters request THEN save characters to local database`() {
        //given
        val api = mockk<RickAndMortyApi> {
            coEvery { getCharacters() } returns CharacterResponse.mock()
        }
        val characterDao = mockk<CharacterDao>(relaxed = true)
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns true
        }

        val wrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, characterDao, networkStateProvider, wrapper)

        //when
        runBlocking { repository.getCharacters() }

        //then
        coVerify { characterDao.saveCharacters(*anyVararg()) }
    }

    @Test
    fun `GIVEN network is disconnected WHEN characters request THEN fetch characters from local database`() {
        //given
        val api = mockk<RickAndMortyApi>(relaxed = true)
        val characterDao = mockk<CharacterDao> {
            coEvery { getCharacters() } returns listOf(
                CharacterCached.mock(),
                CharacterCached.mock()
            )
        }
        val networkStateProvider = mockk<NetworkStateProvider> {
            every { isNetworkAvailable() } returns false
        }

        val wrapper = mockk<ErrorWrapper>(relaxed = true)
        val repository: CharacterRepository =
            CharacterRepositoryImpl(api, characterDao, networkStateProvider, wrapper)

        //when
        runBlocking { repository.getCharacters() }

        //then
        coVerify { characterDao.getCharacters() }
    }
}