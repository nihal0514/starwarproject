package com.example.starwarproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarproject.model.CharacterMoviesResponse
import com.example.starwarproject.model.FilterState
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.pagination.CharacterPagingSource
import com.example.starwarproject.repository.StarWarRepository
import com.example.starwarproject.retrofit.StarWarApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: StarWarRepository,
): ViewModel(){

    private var currentPagingSource: CharacterPagingSource =
        CharacterPagingSource(repository)

    val characterList: Flow<PagingData<ResultsItem>> = Pager(PagingConfig(pageSize = 9)) {
        CharacterPagingSource(repository)
    }.flow


    private val _movieDetailsLiveData = MutableLiveData<List<CharacterMoviesResponse>>()
    val movieDetailsLiveData: LiveData<List<CharacterMoviesResponse>>  get() = _movieDetailsLiveData

    fun fetchMovieDetails(movieUrls: List<String>) {
        viewModelScope.launch {
            try {
                val movieDetailsList = mutableListOf<CharacterMoviesResponse>()

                for (movieUrl in movieUrls) {
                    val response = repository.getMovieDetails(movieUrl)
                    if (response.isSuccessful) {
                        movieDetailsList.add(response.body()!!)
                    } else {
                    }
                }

                _movieDetailsLiveData.postValue(movieDetailsList)
            } catch (e: Exception) {
            }
        }
    }

}