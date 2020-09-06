package com.ciechu.kursakademiaandroida.features.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ciechu.kursakademiaandroida.core.base.BaseViewModel
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapper
import com.ciechu.kursakademiaandroida.features.character.domain.GetCharacterUseCase
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character
import com.ciechu.kursakademiaandroida.features.character.presentation.model.CharacterDisplayable

class CharacterViewModel(private val characterUseCase: GetCharacterUseCase,
                         errorMapper: ErrorMapper
): BaseViewModel(errorMapper) {

    private val _character by lazy {
        MutableLiveData<List<Character>>()
            .also { getCharacter(it) }
    }

    val character: LiveData<List<CharacterDisplayable>> by lazy {
        _character.map { character ->
            character.map { CharacterDisplayable(it) }
        }
    }

    private fun getCharacter(characterLiveData: MutableLiveData<List<Character>>){
        setPendingState()
        characterUseCase(
            params = Unit,
            scope = viewModelScope
        ) { result ->
            setIdleState()
            result.onSuccess { characterLiveData.value = it }
            result.onFailure { handleFailure(it) }
        }
    }
}