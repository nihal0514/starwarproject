package com.example.starwarproject.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.starwarproject.fragment.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)

    fun getMap(): Map<Class<*>, ViewModel>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

}