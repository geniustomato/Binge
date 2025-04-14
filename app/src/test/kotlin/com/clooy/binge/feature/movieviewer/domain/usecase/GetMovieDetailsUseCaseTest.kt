package com.clooy.binge.feature.movieviewer.domain.usecase

import com.clooy.binge.feature.movieviewer.data.utils.generateMoviePosterUrl
import com.clooy.binge.feature.movieviewer.data.utils.toLocalDate
import com.clooy.binge.feature.movieviewer.domain.model.MovieDetails
import com.clooy.binge.feature.movieviewer.domain.model.MovieGenre
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetMovieDetailsUseCaseTest {
    private lateinit var SUT: GetMovieDetailsUseCase

    private val movieRepository: MovieRepository = mockk()

    private val TEST_MOVIE_DETAILS = MovieDetails(
        title = "title",
        releaseDate = "2025-12-25".toLocalDate(),
        rating = 54.3,
        posterUrl = generateMoviePosterUrl(posterPath = "testing"),
        duration = 45,
        genres = List(5) { i -> MovieGenre(name = i.toString()) },
        overview = "theoverview"
    )

    @BeforeEach
    fun setUp() {
        SUT = GetMovieDetailsUseCase(movieRepository)
    }

    @Test
    fun invoke_onSuccess_returnCorrectResult() = runTest {
        val expected = TEST_MOVIE_DETAILS
        coEvery { movieRepository.getMovieFullDetails(movieId = any()) } returns expected

        val actual = SUT(movieId = 123)
        assertEquals(expected, actual)
    }

    @Test
    fun invoke_onFailure_returnCorrectException() = runTest {
        coEvery { movieRepository.getPopularMovieList(page = any()) } throws Exception()
        assertThrows<Exception> { SUT(movieId = 123) }
    }
}
