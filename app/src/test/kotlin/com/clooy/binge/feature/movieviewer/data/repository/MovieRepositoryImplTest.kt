package com.clooy.binge.feature.movieviewer.data.repository

import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieSummaryDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.PopularMoviesListDto
import com.clooy.binge.feature.movieviewer.data.utils.DomainError
import com.clooy.binge.feature.movieviewer.data.utils.DomainResult
import com.clooy.binge.feature.movieviewer.data.utils.toMovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import retrofit2.Response

class MovieRepositoryImplTest {

    private val tmdbService: TmdbService = mockk()

    private lateinit var SUT: MovieRepository

    private val TEST_MOVIE_SUMMARY_DTO = List(10) { i ->
        MovieSummaryDto(
            id = 1,
            title = i.toString(),
            releaseDate = "2025-12-25",
            voteAverage = i.toDouble(),
            posterPath = i.toString()
        )
    }

    @BeforeEach
    fun setUp() {
        SUT = MovieRepositoryImpl(tmdbService = tmdbService)
    }

    @Test
    fun getPopularMovieList_onSuccess_returnCorrectResults() = runTest {
        val expected =
            DomainResult.Success(data = TEST_MOVIE_SUMMARY_DTO.map { it.toMovieSummary() })

        coEvery { tmdbService.getPopularMoviesList(page = any()) } returns Response.success(
            PopularMoviesListDto(
                page = 1,
                results = TEST_MOVIE_SUMMARY_DTO
            )
        )

        when (val actual = SUT.getPopularMovieList(page = 1)) {
            is DomainResult.Success -> assertEquals(expected, actual)
            else -> fail("Invalid result: $actual")
        }
    }

    @Test
    fun getPopularMoviesList_onNullResponseBody_returnNullResponseBody() = runTest {
        val expected = DomainResult.Failure(error = DomainError.NullResponseBody)

        coEvery { tmdbService.getPopularMoviesList(page = any()) } returns Response.success(null)

        when (val actual = SUT.getPopularMovieList(page = 1)) {
            is DomainResult.Success -> {
                fail("Invalid result: $actual")
            }

            else -> assertEquals(expected, actual)
        }
    }
}
