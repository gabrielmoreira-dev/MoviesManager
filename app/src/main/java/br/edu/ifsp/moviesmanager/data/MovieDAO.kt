package br.edu.ifsp.moviesmanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDAO {
    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie ORDER BY name")
    fun getMoviesOrderedByName(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie ORDER BY note")
    fun getMoviesOrderedByNote(): LiveData<List<Movie>>
}