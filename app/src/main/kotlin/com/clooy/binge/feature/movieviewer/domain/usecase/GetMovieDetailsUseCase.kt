package com.clooy.binge.feature.movieviewer.domain.usecase

import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository

internal class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return movieRepository.getMovieFullDetails(movieId = movieId)
    }
}
