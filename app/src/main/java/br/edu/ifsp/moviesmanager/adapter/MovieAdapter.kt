package br.edu.ifsp.moviesmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.moviesmanager.data.Movie
import br.edu.ifsp.moviesmanager.databinding.MovieCellBinding
import com.bumptech.glide.Glide

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var binding: MovieCellBinding
    private var movieList = ArrayList<Movie>()

    fun updateList(newList: ArrayList<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = MovieCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList[position].let {
            holder.name.text = it.name
            holder.genre.text = it.genre.toString()
            Glide.with(holder.itemView).load(it.logo).into(holder.logo)
        }
    }

    override fun getItemCount() = movieList.size

    inner class MovieViewHolder(view: MovieCellBinding): RecyclerView.ViewHolder(view.root) {
        val logo = view.logoIv
        val name = view.nameTv
        val genre = view.genreTv
    }
}