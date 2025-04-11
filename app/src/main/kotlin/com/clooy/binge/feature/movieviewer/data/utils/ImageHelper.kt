package com.clooy.binge.feature.movieviewer.data.utils

internal fun generateMoviePosterUrl(imageSize: ImageSize = ImageSize.W185, posterPath: String) =
    "https://image.tmdb.org/t/p/${imageSize.value}/$posterPath"

internal sealed class ImageSize(val value: String) {
    data object W185 : ImageSize(value = "w185")
    data object W500 : ImageSize(value = "w500")
}
