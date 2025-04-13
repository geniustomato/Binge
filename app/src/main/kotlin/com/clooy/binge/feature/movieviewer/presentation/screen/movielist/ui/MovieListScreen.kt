package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent.Navigation.OnEnterMovie

// Stateful Version
@Composable
internal fun MovieListScreenRoute(
    modifier: Modifier = Modifier,
    // TODO Add viewmodel
    onNavigationEvent: (MovieListScreenEvent.Navigation) -> Unit,
) {
    Column {
        Text("HELLO WORLD")
        Button(onClick = { onNavigationEvent(OnEnterMovie(movieId = 1)) }) {
            Text("ENTER MOVIE 1")
        }
    }
}
