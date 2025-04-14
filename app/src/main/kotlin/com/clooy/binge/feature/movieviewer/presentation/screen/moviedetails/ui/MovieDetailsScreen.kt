package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.state.MovieDetailsState
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.viewmodel.MovieDetailsViewModel

// Stateful Version
@Composable
internal fun MovieDetailsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.loadMovieDetails(movieId = movieId)

    when (uiState) {
        MovieDetailsState.Loading -> Text("LOADING")
        MovieDetailsState.Error -> Text("Error")
        is MovieDetailsState.Success -> {
            val data = (uiState as MovieDetailsState.Success).data
            Column {
                Text("HELLO WORLD")
                Text(data.title)
                Text(data.genres.toString())
                Text(data.year)
                Text(data.synopsis)
            }
        }
    }
}
