package com.ciechu.kursakademiaandroida.features.episodes.presentation

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.ciechu.kursakademiaandroida.core.base.UiState
import com.ciechu.kursakademiaandroida.core.exeption.ErrorMapper
import com.ciechu.kursakademiaandroida.features.episodes.domain.GetEpisodesUseCase
import com.ciechu.kursakademiaandroida.features.episodes.domain.model.Episode
import com.ciechu.kursakademiaandroida.mock.mock
import com.ciechu.kursakademiaandroida.utils.ViewModelTest
import com.ciechu.kursakademiaandroida.utils.getOrAwaitValue
import com.ciechu.kursakademiaandroida.utils.observeForTesting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

internal class EpisodeViewModelTest: ViewModelTest(){

    @Test
    fun `WHEN episode live data is observed THEN set pending state`(){
        //given
        val useCase = mockk<GetEpisodesUseCase>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, errorMapper)

        //when
        viewModel.episodes.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Pending
    }

    @Test
    fun `WHEN episode live data is observed THEN invoke use case to get episodes`(){
        //given
        val useCase = mockk<GetEpisodesUseCase>(relaxed = true)
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, errorMapper)

        //when
        viewModel.episodes.observeForTesting()

        //then
        verify { useCase(viewModel.viewModelScope, Unit, any(), any()) }
    }

    @Test
    fun `GIVEN use case results is success WHEN episode live data is observed THEN set idle state AND set results in live data`(){
        //given
        val episodes = listOf(Episode.mock(), Episode.mock(), Episode.mock())
        val useCase = mockk<GetEpisodesUseCase>{
            every { this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<Episode>>) -> Unit>()(Result.success(episodes))
            }
        }
        val errorMapper = mockk<ErrorMapper>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, errorMapper)

        //when
        viewModel.episodes.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        viewModel.episodes.getOrAwaitValue().forEachIndexed{ index, episodeDisplayable ->
            val episodes = episodes[index]
            episodeDisplayable.name shouldBe episodes.name
            episodeDisplayable.airDate shouldBe episodes.airDate
            episodeDisplayable.code shouldBe episodes.code
        }
    }

    @Test
    fun `GIVEN use case results is failure WHEN episode live data is observed THEN set idle state AND set error message in live data`(){
        //given
        val throwable = Throwable("Ops... Something went wrong")
        val useCase = mockk<GetEpisodesUseCase>{
            every {
                this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<Episode>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val errorMapper = mockk<ErrorMapper> {
            every { map(any()) } returns throwable.message!!
        }
        val observer = mockk<Observer<String>>(relaxed = true)
        val viewModel = EpisodeViewModel(useCase, errorMapper)

        //when
        viewModel.message.observeForever(observer)
        viewModel.episodes.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        verify { observer.onChanged(throwable.message) }
    }
}