package br.edu.ifsp.moviesmanager.repository

import br.edu.ifsp.moviesmanager.data.Movie
import br.edu.ifsp.moviesmanager.data.MovieDAO

class MovieRepository(private val movieDAO: MovieDAO) {
    suspend fun insert(movie: Movie) = movieDAO.insert(movie)

    fun getMoviesOrderedByName() = movieDAO.getMoviesOrderedByName()

    fun getMoviesOrderedByNote() = movieDAO.getMoviesOrderedByNote()

    fun getMovieById(id: Int) = movieDAO.getMovieById(id)
}