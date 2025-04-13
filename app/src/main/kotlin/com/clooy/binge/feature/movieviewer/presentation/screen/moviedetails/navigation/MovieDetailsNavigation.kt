package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.ui.MovieDetailsScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsRoute(val movieId:Int)

fun NavGraphBuilder.movieDetailsScreen(
    navController: NavHostController, // Leaving this in case navigation will be applied
) {
    composable<MovieDetailsRoute> {
        val args = it.toRoute<MovieDetailsRoute>()
        MovieDetailsScreenRoute(movieId = args.movieId)
    }
}

fun NavController.navigateToMovieDetails(movieId: Int) {
    navigate(route = MovieDetailsRoute(movieId = movieId))
}
