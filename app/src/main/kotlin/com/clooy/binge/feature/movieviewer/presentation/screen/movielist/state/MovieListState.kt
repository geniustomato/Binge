package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.state

import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model.MovieListUiModel

internal sealed interface MovieListState {
    data object Loading: MovieListState
    data class Success(val data: MovieListUiModel): MovieListState
    data object Error: MovieListState
}
