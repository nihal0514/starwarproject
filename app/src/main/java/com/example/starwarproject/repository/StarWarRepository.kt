package com.example.starwarproject.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarproject.model.CharacterMoviesResponse
import com.example.starwarproject.model.CharacterResponse
import com.example.starwarproject.model.ResultsItem
import com.example.starwarproject.pagination.CharacterPagingSource
import com.example.starwarproject.retrofit.StarWarApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class StarWarRepository @Inject constructor(
    private val starWarApi: StarWarApi,

){
    private val _characters = MutableLiveData<List<ResultsItem>>()
    val characters: LiveData<List<ResultsItem>> = _characters

    private var currentPage = 1
    private val pageSize = 9

    suspend fun getAllCharacters() {
        val result = starWarApi.getAllCharacters(currentPage)
        if(currentPage<pageSize){
            currentPage++
        }
        result.body()?.let {
            _characters.postValue(result.body()!!.results!!)
        }

    }
    suspend fun getAllCharacters(page: Int): Response<CharacterResponse> {
        return starWarApi.getAllCharacters(page)
    }

    suspend fun getMovieDetails(url: String): Response<CharacterMoviesResponse> {
        return starWarApi.getMoviesDetails(url)
    }



}