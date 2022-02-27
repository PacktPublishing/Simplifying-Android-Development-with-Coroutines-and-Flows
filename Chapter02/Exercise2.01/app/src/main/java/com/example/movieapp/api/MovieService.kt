package com.example.movieapp.api

import com.example.movieapp.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesResponse
}