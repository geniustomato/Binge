package com.clooy.binge.feature.movieviewer.domain.repository

import com.clooy.binge.feature.movieviewer.data.utils.DomainResult
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary

internal interface MovieRepository {
    suspend fun getPopularMovieList(page: Int): DomainResult<List<MovieSummary>>
    suspend fun getMovieFullDetails(movieId: Int): DomainResult<MovieDetails>
}
