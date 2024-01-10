package com.example.starwarproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.R
import com.example.starwarproject.adapters.MovieListAdapter
import com.example.starwarproject.di.ApplicationComponent
import com.example.starwarproject.di.DaggerApplicationComponent
import com.example.starwarproject.viewmodel.CharacterViewModel
import com.example.starwarproject.viewmodel.MainViewModelFactory
import java.util.Arrays
import javax.inject.Inject


class CharacterMovieFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var movieAdapter: MovieListAdapter
    private lateinit var characterListRecyv: RecyclerView

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_character_movie, container, false)

        characterListRecyv = view.findViewById<View>(R.id.moviesListDetails) as RecyclerView

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())

        applicationComponent.injectDetail(this)
        characterViewModel =
            ViewModelProvider(this, mainViewModelFactory)[CharacterViewModel::class.java]

        movieAdapter = MovieListAdapter(arrayListOf())
        characterListRecyv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }

        val filmUrls = listOf(
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/2/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/6/"
        )

        val filmUrlsArray: Array<String> = filmUrls.toTypedArray()

        characterViewModel.fetchMovieDetails(filmUrlsArray)

        observeViewModel()

        return view
    }
    private fun observeViewModel() {

        characterViewModel.movieDetailsLiveData.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                movieAdapter.updateMovies(it)
            }
        }

    }


}