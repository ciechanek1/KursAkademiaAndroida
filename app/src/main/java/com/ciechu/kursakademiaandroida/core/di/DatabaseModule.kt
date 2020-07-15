package com.ciechu.kursakademiaandroida.core.di

import androidx.room.Room
import com.ciechu.kursakademiaandroida.features.data.AppDatabase
import com.ciechu.kursakademiaandroida.features.data.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}