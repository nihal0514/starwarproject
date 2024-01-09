package com.example.starwarproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarproject.model.CharacterResponse
import com.example.starwarproject.repository.StarWarRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: StarWarRepository
): ViewModel(){

    val charactersLiveData: LiveData<CharacterResponse>
        get() = repository.characters


    fun refresh() {
        fetchCountries()
    }
    private fun fetchCountries() {
        viewModelScope.launch {
            repository.getAllCharacters()
        }
    }

}


class TestForMultiBinding @Inject constructor() {
    fun testSomething() {

    }
}