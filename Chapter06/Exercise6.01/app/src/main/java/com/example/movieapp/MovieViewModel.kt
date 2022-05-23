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

class MovieViewModel(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
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
        viewModelScope.launch(dispatcher) {
            movieRepository.fetchMoviesFlow()
                .onStart { _loading.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .catch {
                    _error.postValue("An exception occurred: ${it.message}")
                }
                .collect {
                    _movies.postValue(it)
                }
        }
    }

}