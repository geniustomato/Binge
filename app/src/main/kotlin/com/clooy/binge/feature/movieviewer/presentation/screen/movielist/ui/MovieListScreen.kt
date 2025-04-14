package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.clooy.binge.R
import com.clooy.binge.core.designsystem.component.LoadingScreen
import com.clooy.binge.core.designsystem.component.MessageWithRetry
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.event.MovieListScreenEvent.Navigation
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model.MovieUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.state.MovieListState
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.viewmodel.MovieListViewModel

// Stateful Version
@Composable
internal fun MovieListScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
    onNavigationEvent: (Navigation) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        MovieListState.Loading -> LoadingScreen()
        MovieListState.Error -> MessageWithRetry(
            message = stringResource(R.string.something_went_wrong),
            onRetry = { viewModel.uiState }
        )

        is MovieListState.Success -> {
            val movies = (uiState as MovieListState.Success).data.movies
            MovieScreen(
                movies = movies,
                onNavigationEvent = onNavigationEvent,
                onRefresh = { viewModel.loadMovieList(page = 1) },
                isRefreshing = false
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieScreen(
    movies: List<MovieUiModel>,
    onNavigationEvent: (Navigation) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
) {
    Scaffold(
        topBar = { CenteredTopAppBar(title = "Movies") }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            MovieGrid(
                movies = movies,
                onNavigationEvent = onNavigationEvent,
            )
        }
    }
}

// TODO Make app bar reusable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopAppBar(title: String) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@Composable
internal fun MovieGrid(
    modifier: Modifier = Modifier,
    movies: List<MovieUiModel>,
    onNavigationEvent: (Navigation) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = movies.chunked(2)) { rowMovies ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowMovies.forEach { movie ->
                    MovieGridItem(
                        movie = movie,
                        onNavigationEvent = onNavigationEvent
                    )
                }
                if (rowMovies.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun RowScope.MovieGridItem(
    modifier: Modifier = Modifier,
    movie: MovieUiModel,
    onNavigationEvent: (Navigation) -> Unit
) {
    Column(modifier = modifier.weight(1f), verticalArrangement = Arrangement.Top) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.67f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .clickable(onClick = { onNavigationEvent(Navigation.OnEnterMovie(movieId = movie.id)) }),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = movie.year,
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
