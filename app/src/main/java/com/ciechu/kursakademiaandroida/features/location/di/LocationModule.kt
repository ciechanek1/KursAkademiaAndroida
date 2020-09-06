package com.ciechu.kursakademiaandroida.features.location.di

import com.ciechu.kursakademiaandroida.features.location.LocationRepository
import com.ciechu.kursakademiaandroida.features.location.data.repository.LocationRepositoryImpl
import com.ciechu.kursakademiaandroida.features.location.domain.GetLocationUseCase
import com.ciechu.kursakademiaandroida.features.location.presentation.LocationAdapter
import com.ciechu.kursakademiaandroida.features.location.presentation.LocationFragment
import com.ciechu.kursakademiaandroida.features.location.presentation.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val locationModule = module {
    //data
    factory<LocationRepository> { LocationRepositoryImpl(get(), get(), get()) }

    //domain
    factory { GetLocationUseCase(get()) }

    //presentation
    viewModel { LocationViewModel(get(), get()) }
    factory { LocationFragment() }
    factory { LocationAdapter() }
}