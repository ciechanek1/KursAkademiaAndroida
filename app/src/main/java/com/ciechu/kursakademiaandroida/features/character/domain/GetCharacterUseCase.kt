package com.ciechu.kursakademiaandroida.features.character.domain

import com.ciechu.kursakademiaandroida.core.base.UseCase
import com.ciechu.kursakademiaandroida.features.character.CharacterRepository
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character

class GetCharacterUseCase(private val characterRepository: CharacterRepository): UseCase<List<Character>, Unit>() {
    
    override suspend fun action(params: Unit) = characterRepository.getCharacter()
}