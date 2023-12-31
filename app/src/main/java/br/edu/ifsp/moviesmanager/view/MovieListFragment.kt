package br.edu.ifsp.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.adapter.MovieAdapter
import br.edu.ifsp.moviesmanager.data.Movie
import br.edu.ifsp.moviesmanager.databinding.FragmentMovieListBinding
import br.edu.ifsp.moviesmanager.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        configureRecyclerView()
        binding.apply {
            fab.setOnClickListener { navigateToMovieForm(-1) }
            orderNameBt.setOnClickListener { viewModel.getMoviesOrderedByName() }
            orderNoteBt.setOnClickListener { viewModel.getMoviesOrderedByNote() }
            return root
        }
    }

    private fun configureRecyclerView() {
        movieAdapter = MovieAdapter()
        viewModel.movieList.observe(viewLifecycleOwner) {
            it?.let { movieAdapter.updateList(it as ArrayList<Movie>) }
        }
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
        movieAdapter.setOnClickListener(object : MovieAdapter.MovieListener {
            override fun onItemClicked(pos: Int) = Bundle().let {
                it.putInt("movieId", movieAdapter.movieList[pos].id)
                findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, it)
            }

            override fun onEditItemClicked(pos: Int) {
                navigateToMovieForm(movieAdapter.movieList[pos].id)
            }

            override fun onDeleteItemClicked(pos: Int) {
                viewModel.delete(movieAdapter.movieList[pos])
                Snackbar.make(binding.root, getString(R.string.movie_deleted), Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToMovieForm(id: Int) = Bundle().let {
        it.putInt("movieId", id)
        findNavController().navigate(R.id.action_movieListFragment_to_movieFormFragment, it)
    }
}