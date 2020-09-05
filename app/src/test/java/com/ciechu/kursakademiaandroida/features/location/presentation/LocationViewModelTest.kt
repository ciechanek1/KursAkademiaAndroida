package com.ciechu.kursakademiaandroida.features.location.presentation


import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.ciechu.kursakademiaandroida.core.base.UiState
import com.ciechu.kursakademiaandroida.features.location.domain.GetLocationUseCase
import com.ciechu.kursakademiaandroida.features.location.domain.model.Location
import com.ciechu.kursakademiaandroida.mock.mock
import com.ciechu.kursakademiaandroida.utils.ViewModelTest
import com.ciechu.kursakademiaandroida.utils.getOrAwaitValue
import com.ciechu.kursakademiaandroida.utils.observeForTesting
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class LocationViewModelTest: ViewModelTest() {

    @Test
    fun `WHEN location live data is observed THEN set pending state`(){
        //given
        val useCase = mockk<GetLocationUseCase>(relaxed = true)
        val viewModel = LocationViewModel(useCase)

        //when
        viewModel.location.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Pending
    }

    @Test
    fun `WHEN location live data is observed THEN invoke use case to get locations`(){
        //given
        val useCase = mockk<GetLocationUseCase>(relaxed = true)
        val viewModel = LocationViewModel(useCase)

        //when
        viewModel.location.observeForTesting()

        //then
        verify { useCase(viewModel.viewModelScope, Unit, any(), any()) }
    }

    @Test
    fun `GIVEN use case results is success WHEN location live data is observed THEN set idle state AND set results in live data`(){
        //given
        val location = listOf(Location.mock(), Location.mock(), Location.mock())
        val useCase = mockk<GetLocationUseCase>{
            every { this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<Location>>) -> Unit>()(Result.success(location))
            }
        }
        val viewModel = LocationViewModel(useCase)

        //when
        viewModel.location.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        viewModel.location.getOrAwaitValue().forEachIndexed{ index, locationDisplayable ->
            val location = location[index]
            locationDisplayable.name shouldBe location.name
            locationDisplayable.residents shouldBe location.residents
            locationDisplayable.type shouldBe location.type
        }
    }

    @Test
    fun `GIVEN use case results is failure WHEN episode live data is observed THEN set idle state AND set error message in live data`(){
        //given
        val throwable = Throwable("Ops... Something went wrong")
        val useCase = mockk<GetLocationUseCase>{
            every { this@mockk(any(), any(), any(), any()) } answers {
                lastArg<(Result<List<Location>>) -> Unit>()(Result.failure(throwable))
            }
        }
        val observer = mockk<Observer<String>>(relaxed = true)
        val viewModel = LocationViewModel(useCase)

        //when
        viewModel.message.observeForever(observer)
        viewModel.location.observeForTesting()

        //then
        viewModel.uiState.getOrAwaitValue() shouldBe UiState.Idle
        verify { observer.onChanged(throwable.message) }
    }
}