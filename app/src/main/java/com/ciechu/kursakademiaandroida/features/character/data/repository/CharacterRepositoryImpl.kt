package com.ciechu.kursakademiaandroida.features.character.data.repository

import com.ciechu.kursakademiaandroida.core.api.RickAndMortyApi
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.features.character.data.local.CharacterDao
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterCached
import com.ciechu.kursakademiaandroida.features.character.domain.CharacterRepository
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
    private val characterDao: CharacterDao,
    private val networkStateProvider: NetworkStateProvider
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> {
        return if (networkStateProvider.isNetworkAvailable()) {
            getEpisodesFromRemote()
                .also { saveEpisodesToLocal(it) }
        } else {
            getEpisodesFromLocal()
        }
    }

    private suspend fun getEpisodesFromRemote(): List<Character> {
        return api.getCharacters()
            .results
            .map { it.toCharacter() }
    }

    private suspend fun saveEpisodesToLocal(characters: List<Character>) {
        characters.map { CharacterCached(it) }
            .toTypedArray()
            .let { characterDao.saveCharacters(*it) }
    }

    private suspend fun getEpisodesFromLocal(): List<Character> {
        return characterDao.getCharacters()
            .map { it.toCharacter() }
    }
}