package com.example.starwarproject.retrofit

import com.example.starwarproject.model.CharacterMoviesResponse
import com.example.starwarproject.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarApi {
    @GET("people" )
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET
    suspend fun getMoviesDetails(@Url url: String): Response<CharacterMoviesResponse>
}