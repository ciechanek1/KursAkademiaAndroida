package com.ciechu.kursakademiaandroida.features.character.domain

import com.ciechu.kursakademiaandroida.features.character.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}