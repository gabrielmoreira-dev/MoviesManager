package br.edu.ifsp.moviesmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.data.Movie
import br.edu.ifsp.moviesmanager.databinding.FragmentMovieFormBinding
import br.edu.ifsp.moviesmanager.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar

class MovieFormFragment : Fragment() {
    private lateinit var binding: FragmentMovieFormBinding
    private lateinit var viewModel: MovieViewModel
    private val genres: Array<Movie.Genre> = Movie.Genre.entries.toTypedArray()
    private var selectedGenre = genres[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFormBinding.inflate(inflater, container, false)
        configureSpinner()
        binding.apply {
            saveBt.setOnClickListener { save() }
            cancelBt.setOnClickListener { cancel() }
            return root
        }
    }

    private fun configureSpinner() {
        context?.let {
            val adapter = ArrayAdapter(it, android.R.layout.simple_list_item_1, genres)
            binding.genreSp.adapter = adapter
            binding.genreSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedGenre = genres[p2]
                }
            }
        }
    }

    private fun save() {
        viewModel.insert(getMovie())
        Snackbar.make(binding.root, getString(R.string.movie_added), Snackbar.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun cancel() {
        findNavController().popBackStack()
    }

    private fun getMovie() = binding.let {
        Movie(
            id = 0,
            name = it.nameEt.text.toString(),
            year = it.yearEt.text.toString(),
            producer = it.producerEt.text.toString(),
            duration = it.durationEt.text.toString().toInt(),
            logo = it.logoEt.text.toString(),
            watched = it.watchedCb.isChecked,
            genre = selectedGenre,
            note = null
        )
    }
}