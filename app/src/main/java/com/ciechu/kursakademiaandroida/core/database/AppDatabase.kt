package com.ciechu.kursakademiaandroida.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ciechu.kursakademiaandroida.features.episodes.data.local.EpisodeDao
import com.ciechu.kursakademiaandroida.features.episodes.data.local.model.EpisodeCashed


@Database(entities = [EpisodeCashed::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun episodeDao(): EpisodeDao
}