package br.edu.ifsp.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.databinding.FragmentMovieDetailBinding
import br.edu.ifsp.moviesmanager.viewmodel.MovieViewModel
import com.bumptech.glide.Glide

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieViewModel

    private val movieId: Int by lazy {
        requireArguments().getInt("movieId")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieById(movieId)
        binding.apply {
            viewModel.movie.observe(viewLifecycleOwner) {
                it?.let {
                    nameTv.text = it.name
                    val genre = it.genre.toString().lowercase().capitalize()
                    detailsTv.text =
                        getString(R.string.movie_details, it.producer, genre, it.year)
                    durationTv.text = getString(R.string.duration_min, it.duration.toString())
                    watchedTv.text = getString(R.string.is_watched, if (it.watched) "Yes" else "No")
                    noteTv.text = getString(R.string.note, (it.note ?: 0).toString())
                    Glide.with(view).load(it.logo).into(logoIv)
                }
            }
        }
    }
}