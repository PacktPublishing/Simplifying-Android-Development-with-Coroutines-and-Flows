package com.example.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val movies: LiveData<List<Movie>> = movieRepository.movies
    val error: LiveData<String> = movieRepository.error

    val loading: LiveData<Boolean>
        get() = _loading
    private val _loading = MutableLiveData(true)

    fun fetchMovies() {
        //TODO
    }
}