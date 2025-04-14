package com.clooy.binge.feature.movieviewer.domain.usecase

import com.clooy.binge.feature.movieviewer.data.utils.DomainResult
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository

internal class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): DomainResult<List<MovieSummary>> {
        return movieRepository.getPopularMovieList(page = 1)
    }

}