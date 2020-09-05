package com.ciechu.kursakademiaandroida.features.character.domain

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.Test

internal class GetCharacterUseCaseTest {

    @Test
    fun `when use case is invoke than execute getEpisodes method from repository`(){
        //given
        val repository = mockk<CharacterRepository>(relaxed = true)
        val useCase = GetCharacterUseCase(repository)

        //when
        useCase(
            params = Unit,
            scope = GlobalScope
        )

        //then
        coVerify { repository.getCharacters() }
    }
}