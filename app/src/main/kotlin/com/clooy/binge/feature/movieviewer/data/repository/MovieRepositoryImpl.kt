package com.clooy.binge.feature.movieviewer.data.repository

import com.clooy.binge.core.network.onSuccess
import com.clooy.binge.core.network.safeApiCall
import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import com.clooy.binge.feature.movieviewer.data.utils.DomainResult
import com.clooy.binge.feature.movieviewer.data.utils.toMovieDetails
import com.clooy.binge.feature.movieviewer.data.utils.toMovieSummary
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(private val tmdbService: TmdbService) : MovieRepository {
    override suspend fun getPopularMovieList(page: Int): DomainResult<List<MovieSummary>> =
        safeApiCall { tmdbService.getPopularMoviesList(page = page) }
            .onSuccess { it.results.map { results -> results.toMovieSummary() } }


    override suspend fun getMovieFullDetails(movieId: Int): DomainResult<MovieDetails> =
        safeApiCall { tmdbService.getMovieDetails(movieId = movieId) }
            .onSuccess { it.toMovieDetails() }
}
