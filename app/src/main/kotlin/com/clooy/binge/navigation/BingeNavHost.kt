package com.clooy.binge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.clooy.binge.app.ui.BingeAppState
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.navigation.movieDetailsScreen
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.navigation.MovieListRoute
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.navigation.movieListScreen
import kotlin.reflect.KClass

@Composable
fun BingeNavHost(
    modifier: Modifier = Modifier,
    appState: BingeAppState,
    startDestination: KClass<*> = MovieListRoute::class,
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        movieListScreen(navController = navController)
        movieDetailsScreen(navController = navController)
    }
}
