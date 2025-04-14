package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.model

import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails

internal data class MovieDetailsUiModel(
    val title: String,
    val posterUrl: String,
    val year: String,
    val rating: Double,
    val duration: String,
    val genres: List<String>,
    val synopsis: String,
)


internal fun MovieDetails.toMovieDetailsUiModel() =
    MovieDetailsUiModel(
        title = title,
        posterUrl = posterUrl,
        year = releaseDate.year.toString(),
        rating = rating,
        duration = "convert to time format",
        genres = genres.map { it.name },
        synopsis = overview,
    )
