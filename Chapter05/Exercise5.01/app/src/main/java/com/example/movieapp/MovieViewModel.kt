package com.example.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> = _movies

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    fun fetchMovies() {
        _loading.value = true
        viewModelScope.launch(dispatcher) {
            movieRepository.fetchMoviesFlow()
                .collect {
                    _movies.value = it
                    _loading.value = false
                }
        }
    }

}