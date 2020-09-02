package com.ciechu.kursakademiaandroida.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ciechu.kursakademiaandroida.features.character.data.local.CharacterDao
import com.ciechu.kursakademiaandroida.features.character.data.local.model.CharacterCached
import com.ciechu.kursakademiaandroida.features.episodes.data.local.EpisodeDao
import com.ciechu.kursakademiaandroida.features.episodes.data.local.model.EpisodeCashed
import com.ciechu.kursakademiaandroida.features.location.data.local.LocationDao
import com.ciechu.kursakademiaandroida.features.location.data.local.model.LocationCached


@Database(entities = [EpisodeCashed::class, CharacterCached::class, LocationCached::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun episodeDao(): EpisodeDao
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}