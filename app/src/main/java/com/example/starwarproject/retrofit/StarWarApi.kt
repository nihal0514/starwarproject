package com.example.starwarproject.retrofit

import com.example.starwarproject.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface StarWarApi {
    @GET("people")
    suspend fun getAllCharacters(): Response<CharacterResponse>
}