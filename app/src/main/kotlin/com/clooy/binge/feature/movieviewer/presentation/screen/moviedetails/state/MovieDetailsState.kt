package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.state

import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.model.MovieDetailsUiModel

internal sealed interface MovieDetailsState {
    data object Loading: MovieDetailsState
    data class Success(val data: MovieDetailsUiModel): MovieDetailsState
    data object Error: MovieDetailsState
}