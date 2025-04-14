package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.binge.feature.movieviewer.domain.usecase.GetMovieDetailsUseCase
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.model.toMovieDetailsUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.state.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MovieDetailsState>(value = MovieDetailsState.Loading)
    val uiState: StateFlow<MovieDetailsState> = _uiState

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = try {
                val data = getMovieDetailsUseCase.invoke(movieId = movieId)
                MovieDetailsState.Success(data = data.toMovieDetailsUiModel())
            } catch (e: Exception) {
                MovieDetailsState.Error
            }
        }
    }
}
