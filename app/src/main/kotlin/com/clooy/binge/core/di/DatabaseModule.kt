package com.clooy.binge.core.di

import android.content.Context
import androidx.room.Room
import com.clooy.binge.core.db.BingeDatabase
import com.clooy.binge.feature.movieviewer.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BingeDatabase {
        return Room.databaseBuilder(
            context,
            BingeDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: BingeDatabase): MovieDao {
        return database.movieDao()
    }
}
