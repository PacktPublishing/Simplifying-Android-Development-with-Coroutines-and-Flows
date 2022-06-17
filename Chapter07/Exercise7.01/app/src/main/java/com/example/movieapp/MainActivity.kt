package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

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

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    movieViewModel.movies.collect { movies ->
                        movieAdapter.addMovies(movies)
                    }
                }
                launch {
                    movieViewModel.error.collect { error ->
                        if (error.isNotEmpty()) Snackbar.make(recyclerView, error, Snackbar.LENGTH_LONG).show()
                    }
                }
                launch {
                    movieViewModel.loading.collect { loading ->
                        progressBar.isVisible = loading
                    }
                }
            }
        }
    }
}