package com.clooy.binge.feature.movieviewer.presentation.screen.movielist.viewmodel

import app.cash.turbine.test
import com.clooy.binge.feature.movieviewer.data.utils.generateMoviePosterUrl
import com.clooy.binge.feature.movieviewer.data.utils.toLocalDate
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.usecase.GetMovieListUseCase
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model.MovieListUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.model.toMovieUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.movielist.state.MovieListState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieListViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var SUT: MovieListViewModel

    private val TEST_MOVIE_SUMMARY = List(10) { i ->
        MovieSummary(
            id = i,
            title = i.toString(),
            releaseDate = "2025-12-25".toLocalDate(),
            rating = i.toDouble(),
            posterUrl = generateMoviePosterUrl(posterPath = i.toString())
        )
    }

    private val TEST_MOVIE_UI_MODEL =
        MovieListUiModel(TEST_MOVIE_SUMMARY.map { it.toMovieUiModel() })

    private val getMovieListUseCase: GetMovieListUseCase = mockk()

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun onInit_shouldLoadMovieList() = runTest {
        coEvery { getMovieListUseCase(page = any<Int>()) } returns TEST_MOVIE_SUMMARY

        SUT = MovieListViewModel(getMovieListUseCase)
        coVerify(exactly = 1) { getMovieListUseCase(page = any<Int>()) }
    }

    @Test
    fun loadMovieList_onSuccess_returnSuccessUiState() = runTest {
        // Arrange
        Dispatchers.setMain(testDispatcher)
        SUT = MovieListViewModel(getMovieListUseCase)
        coEvery { getMovieListUseCase(page = any<Int>()) } returns TEST_MOVIE_SUMMARY

        // Act
        SUT.loadMovieList(page = 1)


        // Assert
        SUT.uiState.test {
            assertEquals(MovieListState.Loading, awaitItem())
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(MovieListState.Success(data = TEST_MOVIE_UI_MODEL), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun loadMovieList_onError_returnSuccessUiState() = runTest {
        // Arrange
        Dispatchers.setMain(testDispatcher)
        SUT = MovieListViewModel(getMovieListUseCase)
        coEvery { getMovieListUseCase(page = any<Int>()) } throws NullPointerException()

        // Act
        SUT.loadMovieList(page = 1)


        // Assert
        SUT.uiState.test {
            assertEquals(MovieListState.Loading, awaitItem())
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(MovieListState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
