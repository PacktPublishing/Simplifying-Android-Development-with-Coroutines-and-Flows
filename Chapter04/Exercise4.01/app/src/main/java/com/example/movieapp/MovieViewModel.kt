package com.example.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.movieapp.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {
    val movies: LiveData<List<Movie>> = movieRepository.movies
    val error: LiveData<String> = movieRepository.error

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
}