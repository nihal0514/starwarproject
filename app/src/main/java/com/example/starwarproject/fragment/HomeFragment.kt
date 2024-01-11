package com.example.starwarproject.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.R
import com.example.starwarproject.adapters.CharacterListAdapter
import com.example.starwarproject.adapters.OnClickListener
import com.example.starwarproject.di.ApplicationComponent
import com.example.starwarproject.di.DaggerApplicationComponent
import com.example.starwarproject.model.FilterState
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.pagination.CharacterPagingSource
import com.example.starwarproject.viewmodel.CharacterViewModel
import com.example.starwarproject.viewmodel.MainViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import javax.inject.Inject

class HomeFragment : Fragment(),OnClickListener {

    private lateinit var characterViewModel: CharacterViewModel
    private var characterListAdapter = CharacterListAdapter(this)
    private lateinit var characterListRecyv: RecyclerView

    private lateinit var applicationComponent: ApplicationComponent

    private lateinit var sortBtn: FloatingActionButton

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        characterListRecyv = view.findViewById<View>(R.id.moviesList) as RecyclerView
        sortBtn = view.findViewById<View>(R.id.filter_list) as FloatingActionButton

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())

        applicationComponent.inject(this)
        characterViewModel =
            ViewModelProvider(this, mainViewModelFactory)[CharacterViewModel::class.java]

        characterListRecyv.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = characterListAdapter
        }


        sortBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment(object :
                BottomSheetFragment.FilterListener {
                override fun onFilterApplied(filterState: FilterState) {

                }

            })
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)

        }
        loadAllCharacters()
        return view
    }

    private fun loadAllCharacters() {
        lifecycleScope.launch {
            characterViewModel.characterList.collectLatest {

                characterListAdapter.submitData(it)
            }
        }
    }

    override fun onClick(country: ResultsItem) {
        openCharacterMovieFragment(country)
    }

    private fun openCharacterMovieFragment(country: ResultsItem) {
        val detailsFragment = CharacterMovieFragment()

        val bundle = Bundle()
        bundle.putString("characterFilms", country.films.toString())
        detailsFragment.arguments = bundle

        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, detailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}