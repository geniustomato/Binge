package com.clooy.binge.feature.movieviewer.domain.usecase

import com.clooy.binge.feature.movieviewer.data.utils.generateMoviePosterUrl
import com.clooy.binge.feature.movieviewer.data.utils.toLocalDate
import com.clooy.binge.feature.movieviewer.domain.model.MovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetMovieListUseCaseTest {

    private lateinit var SUT: GetMovieListUseCase

    private val movieRepository: MovieRepository = mockk()

    private val TEST_MOVIE_SUMMARY = List(10) { i ->
        MovieSummary(
            id = i,
            title = i.toString(),
            releaseDate = "2025-12-25".toLocalDate(),
            rating = i.toDouble(),
            posterUrl = generateMoviePosterUrl(posterPath = i.toString())
        )
    }

    @BeforeEach
    fun setUp() {
        SUT = GetMovieListUseCase(movieRepository)
    }

    @Test
    fun invoke_onSuccess_returnCorrectResult() = runTest {
        val expected = TEST_MOVIE_SUMMARY
        coEvery { movieRepository.getPopularMovieList(page = any()) } returns expected

        val actual = SUT(page = 1)
        assertEquals(expected, actual)
    }

    @Test
    fun invoke_onFailure_returnCorrectException() = runTest {
        coEvery { movieRepository.getPopularMovieList(page = any()) } throws Exception()
        assertThrows<Exception> { SUT(page = 1) }
    }
}
