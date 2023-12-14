package br.edu.ifsp.moviesmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.moviesmanager.data.Movie
import br.edu.ifsp.moviesmanager.data.MovieDatabase
import br.edu.ifsp.moviesmanager.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository
    lateinit var movieList: LiveData<List<Movie>>

    init {
        repository = MovieRepository(
            movieDAO = MovieDatabase.getDatabase(application).movieDAO()
        )
        getMoviesOrderedByName()
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }

    fun getMoviesOrderedByName() {
        movieList = repository.getMoviesOrderedByName()
    }

    fun getMoviesOrderedByNote() {
        movieList = repository.getMoviesOrderedByNote()
    }
}