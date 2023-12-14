package br.edu.ifsp.moviesmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val year: String,
    val producer: String,
    val duration: Int,
    val logo: String,
    val watched: Boolean,
    val genre: Genre,
    val note: Int?
) {
    enum class Genre {
        ROMANCE, ADVENTURE, HORROR
    }
}
