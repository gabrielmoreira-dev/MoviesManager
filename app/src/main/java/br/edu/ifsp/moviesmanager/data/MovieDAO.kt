package br.edu.ifsp.moviesmanager.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MovieDAO {
    @Insert
    suspend fun insert(movie: Movie)
}