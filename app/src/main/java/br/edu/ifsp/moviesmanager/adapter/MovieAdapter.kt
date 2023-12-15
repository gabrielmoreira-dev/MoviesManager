package br.edu.ifsp.moviesmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.moviesmanager.data.Movie
import br.edu.ifsp.moviesmanager.databinding.MovieCellBinding
import com.bumptech.glide.Glide

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var binding: MovieCellBinding
    var movieList = ArrayList<Movie>()
    private var listener: MovieListener? = null

    fun updateList(newList: ArrayList<Movie>) {
        movieList = newList
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: MovieListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = MovieCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList[position].let {
            holder.name.text = it.name
            holder.genre.text = it.genre.toString().lowercase().capitalize()
            holder.note.text = "★ ${it.note ?: 0}/10"
            Glide.with(holder.itemView).load(it.logo).into(holder.logo)
        }
    }

    override fun getItemCount() = movieList.size

    inner class MovieViewHolder(view: MovieCellBinding): RecyclerView.ViewHolder(view.root) {
        val logo = view.logoIv
        val name = view.nameTv
        val genre = view.genreTv
        val note = view.noteTv

        init {
            view.root.setOnClickListener {
                listener?.onItemClicked(adapterPosition)
            }
        }
    }

    interface MovieListener {
        fun onItemClicked(pos: Int)
    }
}