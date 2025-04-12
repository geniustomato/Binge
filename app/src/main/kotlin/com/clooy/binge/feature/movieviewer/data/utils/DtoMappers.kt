package com.clooy.binge.feature.movieviewer.data.utils

import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieDetailsDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieGenreDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieSummaryDto
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieGenre
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary

internal fun MovieSummaryDto.toMovieSummary(): MovieSummary =
    MovieSummary(
        title = title,
        releaseDate = releaseDate.toLocalDate(),
        rating = voteAverage,
        posterUrl = generateMoviePosterUrl(imageSize = ImageSize.W185, posterPath = posterPath),
    )

internal fun MovieDetailsDto.toMovieDetails(): MovieDetails =
    MovieDetails(
        title = title,
        releaseDate = releaseDate.toLocalDate(),
        rating = voteAverage,
        posterUrl = generateMoviePosterUrl(imageSize = ImageSize.W185, posterPath = posterPath),
        duration = runtime,
        genres = genres.map { it.toMovieGenre() },
        overview = overview,
    )

internal fun MovieGenreDto.toMovieGenre(): MovieGenre = MovieGenre(name = name)
