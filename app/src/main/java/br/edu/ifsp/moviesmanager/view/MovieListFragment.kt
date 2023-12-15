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
            fab.setOnClickListener { navigateToMovieForm() }
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
        })
    }

    private fun navigateToMovieForm() {
        findNavController().navigate(R.id.action_movieListFragment_to_movieFormFragment)
    }
}