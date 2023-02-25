package com.example.android.networkconnect.api

import com.example.android.networkconnect.GET_CHARACTERS_URL
import com.example.android.networkconnect.api.response.CharacterApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(GET_CHARACTERS_URL)
    suspend fun getCharacters(@Query("page") page: String): CharacterApiResponse
}

