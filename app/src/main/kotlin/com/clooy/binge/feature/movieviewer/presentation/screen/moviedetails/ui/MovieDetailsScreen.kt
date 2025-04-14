package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.clooy.binge.R
import com.clooy.binge.core.designsystem.component.LoadingScreen
import com.clooy.binge.core.designsystem.component.MessageWithRetry
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.model.MovieDetailsUiModel
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
        MovieDetailsState.Loading -> LoadingScreen()
        MovieDetailsState.Error -> MessageWithRetry(
            message = stringResource(R.string.no_network_connection),
            onRetry = { viewModel.loadMovieDetails(movieId = movieId) }
        )

        is MovieDetailsState.Success -> {
            val data = (uiState as MovieDetailsState.Success).data
            MovieDetailsScreen(data = data)
        }
    }
}

@Composable
internal fun MovieDetailsScreen(modifier: Modifier = Modifier, data: MovieDetailsUiModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(data.posterUrl),
            contentDescription = "Poster of ${data.title}",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = data.title,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            text = "${data.year} • ${data.duration} • ⭐ ${data.rating}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(data.genres.size) { index ->
                GenreChip(genre = data.genres[index])
            }
        }

        Text(
            text = data.synopsis,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun GenreChip(genre: String) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = genre,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
        )
    }
}
