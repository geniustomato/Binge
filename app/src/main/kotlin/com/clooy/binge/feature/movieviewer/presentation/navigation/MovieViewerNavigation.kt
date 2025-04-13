package com.clooy.binge.feature.movieviewer.presentation.navigation

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.ui.MovieListScreenRoute
import kotlinx.serialization.Serializable

@Serializable
object MovieListRoute

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
        is MovieListScreenEvent.Navigation.OnEnterMovie -> Toast.makeText(context, "View Movie ${event.movieId} Details", Toast.LENGTH_SHORT).show()
    }
}
