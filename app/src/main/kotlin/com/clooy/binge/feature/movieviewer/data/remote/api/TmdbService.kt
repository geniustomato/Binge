package com.clooy.binge.feature.movieviewer.data.remote.api

import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieFullDetailsDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieVideosListDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.PopularMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: Int = 1): Response<PopularMoviesDto>

    @GET("movie/{movieId}")
    suspend fun getMovieFullDetails(@Path("movieId") movieId: Int): Response<MovieFullDetailsDto>

    @GET("movie/{movieId}/videos")
    suspend fun getMovieVideos(@Path("movieId") movieId: Int): Response<MovieVideosListDto>
}
