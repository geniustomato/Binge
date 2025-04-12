package com.clooy.binge.feature.movieviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class MovieSummaryItemDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterPath: String,
)
