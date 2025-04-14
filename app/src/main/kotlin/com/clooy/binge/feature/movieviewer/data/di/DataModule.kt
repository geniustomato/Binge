package com.clooy.binge.feature.movieviewer.data.di

import com.clooy.binge.feature.movieviewer.data.local.dao.MovieDao
import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import com.clooy.binge.feature.movieviewer.data.repository.MovieRepositoryImpl
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {
    @Provides
    @Singleton
    internal fun tmdbService(retrofit: Retrofit): TmdbService =
        retrofit.create(TmdbService::class.java)

    @Provides
    internal fun movieRepository(tmdbService: TmdbService, movieDao: MovieDao): MovieRepository =
        MovieRepositoryImpl(tmdbService = tmdbService, movieDao = movieDao)
}
