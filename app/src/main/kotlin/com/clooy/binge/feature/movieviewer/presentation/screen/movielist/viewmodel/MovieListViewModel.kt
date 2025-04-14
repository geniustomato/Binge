package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.binge.feature.movieviewer.domain.usecase.GetMovieListUseCase
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model.MovieListUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model.toMovieUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.state.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<MovieListState>(value = MovieListState.Loading)
    val uiState: StateFlow<MovieListState> = _uiState

    init {
        loadMovieList(page = 1)
    }

    fun loadMovieList(page: Int) {
        viewModelScope.launch {
            _uiState.value = try {
                val data = getMovieListUseCase.invoke(page = page)
                MovieListState.Success(data = MovieListUiModel(movies = data.map { it.toMovieUiModel() }))
            } catch (e: Exception) {
                MovieListState.Error
            }
        }
    }
}
