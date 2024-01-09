package com.example.starwarproject.di

import androidx.lifecycle.ViewModel
import com.example.starwarproject.viewmodel.CharacterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(CharacterViewModel::class)
    @IntoMap
    abstract fun characterViewModel(characterViewModel: CharacterViewModel): ViewModel

}