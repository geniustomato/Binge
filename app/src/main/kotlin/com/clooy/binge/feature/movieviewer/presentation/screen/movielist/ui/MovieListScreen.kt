package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent.Navigation.OnEnterMovie
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.state.MovieListState
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.viewmodel.MovieListViewModel

// Stateful Version
@Composable
internal fun MovieListScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
    onNavigationEvent: (MovieListScreenEvent.Navigation) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        MovieListState.Loading -> Text("LOADING")
        MovieListState.Error -> Text("Error")
        is MovieListState.Success -> {
            val data = (uiState as MovieListState.Success).data.movies.first()
            Column {
                Text("HELLO WORLD")
                Button(onClick = { onNavigationEvent(OnEnterMovie(movieId = data.id)) }) {
                    Text("ENTER MOVIE ${data.title}")
                }
            }
        }
    }
}
