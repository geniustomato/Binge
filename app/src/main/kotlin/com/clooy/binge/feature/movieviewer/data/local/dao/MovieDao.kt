package com.clooy.binge.feature.movieviewer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.clooy.binge.feature.movieviewer.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE isSynced = 0")
    suspend fun getUnsynced(): List<MovieEntity>

    @Update
    suspend fun updateAll(movies: List<MovieEntity>)
}
