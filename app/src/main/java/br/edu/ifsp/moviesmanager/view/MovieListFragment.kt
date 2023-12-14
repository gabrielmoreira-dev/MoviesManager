package br.edu.ifsp.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.databinding.FragmentMovieListBinding
import br.edu.ifsp.moviesmanager.viewmodel.MovieViewModel

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.apply {
            fab.setOnClickListener { navigateToMovieForm() }
            return root
        }
    }

    private fun navigateToMovieForm() {
        findNavController().navigate(R.id.action_movieListFragment_to_movieFormFragment)
    }
}