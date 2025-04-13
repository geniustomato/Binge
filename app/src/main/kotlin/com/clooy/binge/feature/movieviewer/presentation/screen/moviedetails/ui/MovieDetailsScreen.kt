package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Stateful Version
@Composable
fun MovieDetailsScreenRoute(
    modifier: Modifier = Modifier,
    // TODO Add viewmodel
    movieId: Int,
) {
    Column {
        Text("You're inside movie $movieId")
    }
}
