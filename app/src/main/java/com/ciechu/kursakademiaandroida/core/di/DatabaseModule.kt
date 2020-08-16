package com.ciechu.kursakademiaandroida.core.di

import androidx.room.Room
import com.ciechu.kursakademiaandroida.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "appDatabase"
        ).build()
    }

    single { get<AppDatabase>().episodeDao() }
}