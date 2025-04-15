package com.clooy.binge.feature.movieviewer.data.repository

import android.util.Log
import com.clooy.binge.feature.movieviewer.data.local.dao.MovieDao
import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import com.clooy.binge.feature.movieviewer.data.utils.toDto
import com.clooy.binge.feature.movieviewer.data.utils.toEntity
import com.clooy.binge.feature.movieviewer.data.utils.toMovieDetails
import com.clooy.binge.feature.movieviewer.data.utils.toMovieSummary
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import okio.IOException
import javax.inject.Inject

internal class MovieRepositoryImpl
@Inject constructor(
    private val tmdbService: TmdbService,
    private val movieDao: MovieDao,
) : MovieRepository {

    override suspend fun getPopularMovieList(page: Int): List<MovieSummary> = try {
        val response = tmdbService.getPopularMoviesList(page = page)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val data = response.body()!!.results
                movieDao.insertAll(data.map { it.toEntity() })
                data.sortedByDescending { it.popularity }.map { it.toMovieSummary() }
            } else {
                throw NullPointerException("Null response body")
            }
        } else {
            throw Exception("Unsuccessful response")
        }
    } catch (e: Exception) {
        if (e is IOException) {
            movieDao.getAll().sortedByDescending { it.popularity }.map { it.toDto().toMovieSummary() }
        } else {
            throw e
        }
    }


    override suspend fun getMovieFullDetails(movieId: Int): MovieDetails = try {
        val response = tmdbService.getMovieDetails(movieId = movieId)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                response.body()!!.toMovieDetails()
            } else {
                throw NullPointerException("Null response body")
            }
        } else {
            throw Exception("Unsuccessful response")
        }
    } catch (e: Exception) {
        throw e
    }
}
