package com.ciechu.kursakademiaandroida.features.episodes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ciechu.kursakademiaandroida.features.episodes.data.local.model.EpisodeCashed

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM EpisodeCashed")
    suspend fun getEpisodes(): List<EpisodeCashed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEpisodes(vararg episode: EpisodeCashed)
}