package com.clooy.binge.feature.movieviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class MovieFullDetailsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("genres") val genres: List<MovieGenreDto>,
    @SerializedName("overview") val overview: String,
)

internal data class MovieGenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
