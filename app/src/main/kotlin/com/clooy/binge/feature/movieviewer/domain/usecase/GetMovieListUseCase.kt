package com.clooy.binge.feature.movieviewer.domain.usecase

import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository

internal class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(page: Int): List<MovieSummary> {
        return movieRepository.getPopularMovieList(page = page)
    }
}
