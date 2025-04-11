package com.clooy.binge.feature.movieviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class PopularMoviesDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieListItemDto>,
)
