package com.clooy.binge.feature.movieviewer.data.di

import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class MovieViewerModule {
    @Provides
    @Singleton
    internal fun tmdbService(retrofit: Retrofit): TmdbService = retrofit.create(TmdbService::class.java)
}
