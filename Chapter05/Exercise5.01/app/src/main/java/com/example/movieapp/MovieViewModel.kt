package com.example.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.movieapp.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {
    val movies: LiveData<List<Movie>>
        get() = _movies
    private val _movies = MutableLiveData(emptyList<Movie>())

    val error: LiveData<String>
        get() = _error
    private val _error = MutableLiveData<String>()

    val loading: LiveData<Boolean>
        get() = _loading
    private val _loading = MutableLiveData(true)

    fun fetchMovies() {
        _loading.value = true
        viewModelScope.launch(dispatcher) {
            movieRepository.fetchMovies()
            _loading.postValue(false)
        }
    }

    fun fetchMoviesByFlow() {
        viewModelScope.launch {
            movieRepository.fetchMoviesFlow()
                .onStart { _loading.value = true }
                .onCompletion { _loading.value = false }
                .collect {
                    _movies.value = it
                }
        }
    }

}