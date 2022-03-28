package com.example.movieapp.model

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>
)