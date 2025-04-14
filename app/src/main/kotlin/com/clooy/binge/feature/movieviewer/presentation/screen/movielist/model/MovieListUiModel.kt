package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model

import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary

internal data class MovieListUiModel(
    val movies: List<MovieUiModel>
)

internal data class MovieUiModel(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val year: String,
    val rating: Double,
)



internal fun MovieSummary.toMovieUiModel() =
    MovieUiModel(
        id = id,
        title = title,
        posterUrl = posterUrl,
        year = releaseDate.year.toString(),
        rating = rating,
    )
