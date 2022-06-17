package com.example.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        val expectedMovies = MutableLiveData<List<Movie>>()
        expectedMovies.postValue(listOf(Movie(title = "Movie")))

        val movieRepository: MovieRepository = mock {
            onBlocking { movies } doReturn expectedMovies
        }

        val movieViewModel = MovieViewModel(movieRepository)
        assertEquals(expectedMovies.value, movieViewModel.movies.value)
    }

    @Test
    fun loading() {
        val movieRepository: MovieRepository = mock()
        val dispatcher = StandardTestDispatcher()

        runTest {
            val movieViewModel = MovieViewModel(movieRepository, dispatcher)
            movieViewModel.fetchMovies()

            assertTrue(movieViewModel.loading.value == true)
            dispatcher.scheduler.advanceUntilIdle()
            assertFalse(movieViewModel.loading.value == true)
        }
    }

}