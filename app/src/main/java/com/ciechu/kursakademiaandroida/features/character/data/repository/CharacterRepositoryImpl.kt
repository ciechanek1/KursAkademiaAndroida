package com.ciechu.kursakademiaandroida.features.character.data.repository

import com.ciechu.kursakademiaandroida.core.api.RickAndMortyApi
import com.ciechu.kursakademiaandroida.core.exeption.ErrorWrapper
import com.ciechu.kursakademiaandroida.core.exeption.callOrThrow
import com.ciechu.kursakademiaandroida.core.network.NetworkStateProvider
import com.ciechu.kursakademiaandroida.features.character.data.local.CharacterDao
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterCached
import com.ciechu.kursakademiaandroida.features.character.domain.CharacterRepository
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
    private val characterDao: CharacterDao,
    private val networkStateProvider: NetworkStateProvider,
    private val errorWrapper: ErrorWrapper
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> {
        return if (networkStateProvider.isNetworkAvailable()) {
            callOrThrow(errorWrapper){
                getCharacterFromRemote()
            }
                .also { saveCharacterToLocal(it) }
        } else {
            getCharacterFromLocal()
        }
    }

    private suspend fun getCharacterFromRemote(): List<Character> {
        return api.getCharacters()
            .results
            .map { it.toCharacter() }
    }

    private suspend fun saveCharacterToLocal(characters: List<Character>) {
        characters.map { CharacterCached(it) }
            .toTypedArray()
            .let { characterDao.saveCharacters(*it) }
    }

    private suspend fun getCharacterFromLocal(): List<Character> {
        return characterDao.getCharacters()
            .map { it.toCharacter() }
    }
}