package com.clooy.binge.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clooy.binge.feature.movieviewer.data.local.dao.MovieDao
import com.clooy.binge.feature.movieviewer.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class BingeDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var INSTANCE: BingeDatabase? = null

        fun getDatabase(context: Context): BingeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    BingeDatabase::class.java,
                    "movie_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}