package com.example.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.example.movieapp.model.Movie

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    init {
        fetchMovies()
    }

    val movies: LiveData<List<Movie>>
        get() = movieRepository.movies

    fun getError(): LiveData<String> = movieRepository.error

    private fun fetchMovies() {

    }
}