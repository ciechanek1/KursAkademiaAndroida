package com.ciechu.kursakademiaandroida.features.character

import com.ciechu.kursakademiaandroida.features.character.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacter(): List<Character>
}