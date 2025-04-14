package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.navigation.navigateToMovieDetails
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.ui.MovieListScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object MovieListRoute

fun NavGraphBuilder.movieListScreen(
    navController: NavHostController,
) {
    composable<MovieListRoute> {
        MovieListScreenRoute(onNavigationEvent = navController::handleMovieListNavigationEvent)
    }
}

private fun NavController.handleMovieListNavigationEvent(
    event: MovieListScreenEvent.Navigation
) {
    when (event) {
        is MovieListScreenEvent.Navigation.OnEnterMovie -> navigateToMovieDetails(movieId = event.movieId)
    }
}
