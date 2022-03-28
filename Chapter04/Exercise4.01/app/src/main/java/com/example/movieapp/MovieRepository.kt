package com.example.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.api.MovieService
import com.example.movieapp.model.Movie

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "your_api_key_here"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    private val errorLiveData = MutableLiveData<String>()

    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val movies = movieService.getMovies(apiKey)
            movieLiveData.postValue(movies.results)
        } catch (exception: Exception) {
            errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}