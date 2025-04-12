package com.clooy.binge.feature.movieviewer.data.remote.api

import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieDetailsDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieVideosDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.PopularMoviesListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: Int): Response<PopularMoviesListDto>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): Response<MovieDetailsDto>

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideos(@Path("movieId") movieId: Int): Response<MovieVideosDto>
}
