package com.example.starwarproject.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starwarproject.model.CharacterResponse
import com.example.starwarproject.retrofit.StarWarApi
import javax.inject.Inject

class StarWarRepository @Inject constructor(
    private val starWarApi: StarWarApi,

){
    private val _characters = MutableLiveData<CharacterResponse>()
    val characters: LiveData<CharacterResponse> = _characters

    suspend fun getAllCharacters() {
        val result = starWarApi.getAllCharacters()
        result.body()?.let {
          Log.d("moviecharactes",result.toString())
        }

    }
}