package com.clooy.binge.feature.movieviewer.data.repository

import com.clooy.binge.core.network.ApiResult
import com.clooy.binge.core.network.map
import com.clooy.binge.core.network.mapError
import com.clooy.binge.core.network.safeApiCall
import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import com.clooy.binge.feature.movieviewer.data.utils.toMovieDetails
import com.clooy.binge.feature.movieviewer.data.utils.toMovieSummary
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(private val tmdbService: TmdbService) : MovieRepository {
    override suspend fun getPopularMovieList(page: Int): ApiResult<List<MovieSummary>> =
        safeApiCall { tmdbService.getPopularMoviesList(page = page) }
            .map { data -> data.results.map { it.toMovieSummary() } }
            .mapError { throwable -> throw Exception(throwable) }

    override suspend fun getMovieFullDetails(movieId: Int): ApiResult<MovieDetails> =
        safeApiCall { tmdbService.getMovieDetails(movieId = movieId) }
            .map { data -> data.toMovieDetails() }
            .mapError { throwable -> throw Exception(throwable) }
}
