package com.clooy.binge.feature.movieviewer.data.utils

import com.clooy.binge.feature.movieviewer.data.local.entity.MovieEntity
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieSummaryDto

internal fun MovieSummaryDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        posterPath = posterPath
    )
}

internal fun MovieEntity.toDto(): MovieSummaryDto {
    return MovieSummaryDto(
        id = id,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        posterPath = posterPath
    )
}