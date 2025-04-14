package com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.viewmodel

import app.cash.turbine.test
import com.clooy.binge.feature.movieviewer.data.utils.generateMoviePosterUrl
import com.clooy.binge.feature.movieviewer.data.utils.toLocalDate
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieGenre
import com.clooy.binge.feature.movieviewer.domain.usecase.GetMovieDetailsUseCase
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.model.toMovieDetailsUiModel
import com.clooy.binge.feature.movieviewer.presentation.screen.moviedetails.state.MovieDetailsState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MovieDetailsViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var SUT: MovieDetailsViewModel

    private val TEST_MOVIE_DETAILS = MovieDetails(
        title = "title",
        releaseDate = "2025-12-25".toLocalDate(),
        rating = 54.3,
        posterUrl = generateMoviePosterUrl(posterPath = "testing"),
        duration = 45,
        genres = List(5) { i -> MovieGenre(name = i.toString()) },
        overview = "theoverview"
    )

    private val TEST_MOVIE_DETAILS_UI_MODEL = TEST_MOVIE_DETAILS.toMovieDetailsUiModel()

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadMovieDetails_onSuccess_returnSuccessUiState() = runTest {
        // Arrange
        Dispatchers.setMain(testDispatcher)
        SUT = MovieDetailsViewModel(getMovieDetailsUseCase)
        coEvery { getMovieDetailsUseCase(movieId = any<Int>()) } returns TEST_MOVIE_DETAILS

        // Act
        SUT.loadMovieDetails(movieId = 1)


        // Assert
        SUT.uiState.test {
            assertEquals(MovieDetailsState.Loading, awaitItem())
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(MovieDetailsState.Success(data = TEST_MOVIE_DETAILS_UI_MODEL), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun loadMovieDetails_onError_returnSuccessUiState() = runTest {
        // Arrange
        Dispatchers.setMain(testDispatcher)
        SUT = MovieDetailsViewModel(getMovieDetailsUseCase)
        coEvery { getMovieDetailsUseCase(movieId = any<Int>()) } throws NullPointerException()

        // Act
        SUT.loadMovieDetails(movieId = 1)


        // Assert
        SUT.uiState.test {
            assertEquals(MovieDetailsState.Loading, awaitItem())
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(MovieDetailsState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
