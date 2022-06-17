package com.example.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun fetchMovies() {
        val dispatcher = StandardTestDispatcher()

        val movies = listOf(Movie(title = "Movie"))
        val expectedMovies = MutableLiveData<List<Movie>>()
        expectedMovies.postValue(movies)

        val movieRepository: MovieRepository = mock {
            onBlocking { fetchMoviesFlow() } doReturn flowOf(movies)
        }

        val movieViewModel = MovieViewModel(movieRepository, dispatcher)

        runTest {
            movieViewModel.fetchMovies()
            dispatcher.scheduler.advanceUntilIdle()
            assertEquals(expectedMovies.value, movieViewModel.movies.value)
        }
    }

    @Test
    fun loading() {
        val movieRepository: MovieRepository = mock()
        val dispatcher = StandardTestDispatcher()

        runTest {
            val movieViewModel = MovieViewModel(movieRepository, dispatcher)
            movieViewModel.fetchMovies()

            assertTrue(movieViewModel.loading.value)
            dispatcher.scheduler.advanceUntilIdle()
            assertFalse(movieViewModel.loading.value)
        }
    }

}