package com.ciechu.kursakademiaandroida.features.character.presentation

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.ciechu.kursakademiaandroida.core.base.UiState
import com.ciechu.kursakademiaandroida.features.character.domain.GetCharacterUseCase
import com.ciechu.kursakademiaandroida.features.character.domain.model.Character
import com.ciechu.kursakademiaandroida.mock.mock
import com.ciechu.kursakademiaandroida.utils.ViewModelTest
import com.ciechu.kursakademiaandroida.utils.getOrAwaitValue
import com.ciechu.kursakademiaandroida.utils.observeForTesting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class CharacterViewModelTest: ViewModelTest() {

    @Test
    fun `WHEN character live data is observed THEN set pending state`(){
        //given
        val useCase = mockk<GetCharacterUseCase>(relaxed = true)
        val viewModel = CharacterViewModel(useCase)

        //when
        viewModel.character.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Pending
    }

    @Test
    fun `WHEN character live data is observed THEN invoke use case to get characters`(){
        //given
        val useCase = mockk<GetCharacterUseCase>(relaxed = true)
        val viewModel = CharacterViewModel(useCase)

        //when
        viewModel.character.observeForTesting()

        //then
        verify { useCase(viewModel.viewModelScope, Unit, any(), any()) }
    }

    @Test
    fun `GIVEN use case results is success WHEN character live data is observed THEN set idle state AND set results in live data`(){
        //given
        val character = listOf(Character.mock(), Character.mock(), Character.mock())
        val useCase = mockk<GetCharacterUseCase>{
            every { this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<Character>>) -> Unit>()(Result.success(character))
            }
        }
        val viewModel = CharacterViewModel(useCase)

        //when
        viewModel.character.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        viewModel.character.getOrAwaitValue().forEachIndexed{ index, characterDisplayable ->
            val character = character[index]
            characterDisplayable.name shouldBe character.name
            characterDisplayable.status shouldBe character.status
            character.type shouldBe character.type
        }
    }

    @Test
    fun `GIVEN use case results is failure WHEN episode live data is observed THEN set idle state AND set error message in live data`(){
        //given
        val throwable = Throwable("Ops... Something went wrong")
        val useCase = mockk<GetCharacterUseCase>{
            every { this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<Character>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val observer = mockk<Observer<String>>(relaxed = true)
        val viewModel = CharacterViewModel(useCase)

        //when
        viewModel.message.observeForever(observer)
        viewModel.character.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        verify { observer.onChanged(throwable.message) }
    }

}