package com.clooy.binge.feature.movieviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class MovieVideosListDto(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<MovieVideoDto>,
)

internal data class MovieVideoDto(
    @SerializedName("site") val site: String,
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("published_at") val publishedAt: String,
)
