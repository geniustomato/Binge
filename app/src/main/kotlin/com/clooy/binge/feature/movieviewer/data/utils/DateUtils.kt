package com.clooy.binge.feature.movieviewer.data.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun String.toLocalDate(pattern: String = "yyyy-MM-dd"): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDate.parse(this, formatter)
}
