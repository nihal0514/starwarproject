package com.example.starwarproject.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.R
import com.example.starwarproject.adapters.CharacterListAdapter
import com.example.starwarproject.adapters.OnClickListener
import com.example.starwarproject.di.ApplicationComponent
import com.example.starwarproject.di.DaggerApplicationComponent
import com.example.starwarproject.viewmodel.CharacterViewModel
import com.example.starwarproject.viewmodel.MainViewModelFactory

import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var characterListAdapter: CharacterListAdapter
    private lateinit var characterListRecyv: RecyclerView

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        characterListRecyv = view.findViewById<View>(R.id.moviesList) as RecyclerView

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())

        applicationComponent.inject(this)
        characterViewModel = ViewModelProvider(this, mainViewModelFactory)[CharacterViewModel::class.java]
        characterViewModel.refresh()

        characterListAdapter = CharacterListAdapter(arrayListOf(), OnClickListener {

        })

        characterListRecyv.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = characterListAdapter
        }
        observeViewModel()

        return view

    }
    private fun observeViewModel() {

        characterViewModel.charactersLiveData.observe(viewLifecycleOwner) { characters ->
            characters?.let {
                characterListRecyv.visibility = View.VISIBLE
                //movieList.addAll(it)
                characterListAdapter.updateMovies(it)
            }
        }

        /*characterViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    moviesListrecyv.visibility = View.GONE
                }
            }
        }*/
    }

}