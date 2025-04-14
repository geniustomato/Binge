package com.clooy.binge.feature.movieviewer.domain.di

import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import com.clooy.binge.feature.movieviewer.domain.usecase.GetMovieDetailsUseCase
import com.clooy.binge.feature.movieviewer.domain.usecase.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class DataModule {
    @Provides
    internal fun getMovieListUseCase(movieRepository: MovieRepository) =
        GetMovieListUseCase(movieRepository = movieRepository)

    @Provides
    internal fun getMovieDetailsUseCase(movieRepository: MovieRepository) =
        GetMovieDetailsUseCase(movieRepository = movieRepository)
}
