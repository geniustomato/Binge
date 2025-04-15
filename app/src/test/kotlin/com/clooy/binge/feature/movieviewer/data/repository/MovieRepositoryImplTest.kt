package com.clooy.binge.feature.movieviewer.data.repository

import com.clooy.binge.feature.movieviewer.data.local.dao.MovieDao
import com.clooy.binge.feature.movieviewer.data.remote.api.TmdbService
import com.clooy.binge.feature.movieviewer.data.remote.dto.MovieSummaryDto
import com.clooy.binge.feature.movieviewer.data.remote.dto.PopularMoviesListDto
import com.clooy.binge.feature.movieviewer.data.utils.toEntity
import com.clooy.binge.feature.movieviewer.data.utils.toMovieSummary
import com.clooy.binge.feature.movieviewer.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Response

class MovieRepositoryImplTest {

    private val tmdbService: TmdbService = mockk()

    private val movieDao: MovieDao = mockk(relaxed = true)

    private lateinit var SUT: MovieRepository

    private val TEST_MOVIE_SUMMARY_DTO = List(10) { i ->
        MovieSummaryDto(
            id = 1,
            title = i.toString(),
            releaseDate = "2025-12-25",
            voteAverage = i.toDouble(),
            posterPath = i.toString(),
            popularity = i.toDouble()
        )
    }

    @BeforeEach
    fun setUp() {
        SUT = MovieRepositoryImpl(tmdbService = tmdbService, movieDao = movieDao)
    }

    @Test
    fun getPopularMovieList_onSuccess_returnCorrectResults() = runTest {
        val expected =
            TEST_MOVIE_SUMMARY_DTO.sortedByDescending { it.popularity }.map { it.toMovieSummary() }

        coEvery { tmdbService.getPopularMoviesList(page = any()) } returns Response.success(
            PopularMoviesListDto(
                page = 1,
                results = TEST_MOVIE_SUMMARY_DTO
            )
        )

        val actual = SUT.getPopularMovieList(page = 1)
        assertEquals(expected, actual)
    }

    @Test
    fun getPopularMoviesList_onNullResponseBody_returnNullResponseBody() = runTest {
        coEvery { tmdbService.getPopularMoviesList(page = any()) } returns Response.success(null)

        assertThrows<NullPointerException> { SUT.getPopularMovieList(page = 1) }
    }

    @Test
    fun getPopularMoviesList_onNetworkError_returnOfflineData() = runTest {
        val expected =
            TEST_MOVIE_SUMMARY_DTO.sortedByDescending { it.popularity }.map { it.toMovieSummary() }

        coEvery { tmdbService.getPopularMoviesList(page = any()) } throws IOException("Network error")
        coEvery { movieDao.getAll() } returns TEST_MOVIE_SUMMARY_DTO.sortedByDescending { it.popularity }
            .map { it.toEntity() }

        val actual = SUT.getPopularMovieList(page = 1)

        assertEquals(expected, actual)

    }
}
