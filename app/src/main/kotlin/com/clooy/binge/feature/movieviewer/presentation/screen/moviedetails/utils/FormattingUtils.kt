package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.utils

fun formatMinutesToHoursAndMinutes(totalMinutes: Int): String {
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
}