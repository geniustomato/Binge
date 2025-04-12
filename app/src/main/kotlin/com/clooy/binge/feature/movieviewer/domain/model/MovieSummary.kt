package com.clooy.binge.feature.movieviewer.domain.model

import org.threeten.bp.LocalDate

data class MovieSummary(
    val title: String,
    val releaseDate: LocalDate,
    val rating: Double,
    val posterUrl: String,
)

data class MovieDetails(
    val title: String,
    val releaseDate: LocalDate,
    val rating: Double,
    val posterUrl: String,
    val duration: Int,
    val genres: List<MovieGenre>,
    val overview: String,
)

data class MovieGenre(
    val name: String,
)
