package com.ciechu.kursakademiaandroida.features.character.di

import com.ciechu.kursakademiaandroida.features.character.data.repository.CharacterRepositoryImpl
import com.ciechu.kursakademiaandroida.features.character.domain.CharacterRepository
import com.ciechu.kursakademiaandroida.features.character.domain.GetCharacterUseCase
import com.ciechu.kursakademiaandroida.features.character.presentation.CharacterAdapter
import com.ciechu.kursakademiaandroida.features.character.presentation.CharacterFragment
import com.ciechu.kursakademiaandroida.features.character.presentation.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {
    //data
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get(), get()) }

    //domain
    factory { GetCharacterUseCase(get()) }

    //presentation
    viewModel { CharacterViewModel(get(), get()) }
    factory { CharacterFragment() }
    factory { CharacterAdapter() }
}