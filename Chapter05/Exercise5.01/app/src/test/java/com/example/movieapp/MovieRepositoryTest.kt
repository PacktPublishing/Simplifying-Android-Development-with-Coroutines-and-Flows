package com.example.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.api.MovieService
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MoviesResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun fetchMovies() {
        val movies = listOf(Movie(id = 3), Movie(id = 4))
        val response = MoviesResponse(1, movies)

        val movieService: MovieService = mock {
            onBlocking { getMovies(anyString()) } doReturn response
        }
        val movieRepository = MovieRepository(movieService)

        runTest {
            movieRepository.fetchMovies()
            val movieLiveData = movieRepository.movies
            assertEquals(movies, movieLiveData.value)
        }
    }

    @Test
    fun fetchMoviesWithError() {
        val exception = "Test Exception"

        val movieService: MovieService = mock {
            onBlocking { getMovies(anyString()) } doThrow RuntimeException(exception)
        }
        val movieRepository = MovieRepository(movieService)

        runTest {
            movieRepository.fetchMovies()

            val movieLiveData = movieRepository.movies
            assertNull(movieLiveData.value)

            val errorLiveData = movieRepository.error
            assertNotNull(errorLiveData.value)
            assertTrue(errorLiveData.value.toString().contains(exception))
        }
    }
}