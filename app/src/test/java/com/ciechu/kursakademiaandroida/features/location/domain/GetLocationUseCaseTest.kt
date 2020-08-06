package com.ciechu.kursakademiaandroida.features.location.domain

import com.ciechu.kursakademiaandroida.features.location.LocationRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.Test

internal class GetLocationUseCaseTest {

    @Test
    fun `when use case is invoke than execute getEpisodes method from repository`(){
        //given
        val repository = mockk<LocationRepository>(relaxed = true)
        val useCase = GetLocationUseCase(repository)
        //when
        useCase(
            params = Unit,
            scope = GlobalScope
        )

        //then
        coVerify { repository.getLocation() }
    }
}