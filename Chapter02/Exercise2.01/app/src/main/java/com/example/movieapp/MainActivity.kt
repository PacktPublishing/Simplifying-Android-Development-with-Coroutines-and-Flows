package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val movieAdapter by lazy {
        MovieAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.movie_list)
        recyclerView.adapter = movieAdapter

        val movieRepository = (application as MovieApplication).movieRepository
        val movieViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieViewModel(movieRepository) as T
            }
        })[MovieViewModel::class.java]
        movieViewModel.fetchMovies()

        movieViewModel.movies.observe(this) { movies ->
            movieAdapter.addMovies(movies)
        }
        movieViewModel.error.observe(this) { error ->
            Snackbar.make(recyclerView, error, Snackbar.LENGTH_LONG).show()
        }

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        movieViewModel.loading.observe(this) { loading ->
            progressBar.isVisible = loading
        }
    }
}