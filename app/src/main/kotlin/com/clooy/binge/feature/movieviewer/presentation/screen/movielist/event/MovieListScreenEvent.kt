package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event

internal sealed interface MovieListScreenEvent {

    sealed interface Navigation: MovieListScreenEvent {
        data class OnEnterMovie(val movieId: Int) : Navigation
    }
}
