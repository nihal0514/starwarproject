package com.example.starwarproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarproject.R
import com.example.starwarproject.di.ApplicationComponent
import com.example.starwarproject.di.DaggerApplicationComponent
import com.example.starwarproject.viewmodel.CharacterViewModel
import com.example.starwarproject.viewmodel.MainViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var characterViewModel: CharacterViewModel

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())

        applicationComponent.inject(this)

        characterViewModel = ViewModelProvider(this, mainViewModelFactory)[CharacterViewModel::class.java]

        characterViewModel.refresh()

        return view

    }
}