package com.example.android.networkconnect.api

import com.example.android.networkconnect.BASE_URL
import com.example.android.networkconnect.GET_CHARACTERS_URL
import com.example.android.networkconnect.api.response.CharacterApiResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val okkHttpBuilder = OkHttpClient.Builder().build()

private val retrofit =
    Retrofit.Builder().client(okkHttpBuilder).baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create()).build()

interface ApiService {
    @GET(GET_CHARACTERS_URL)
    suspend fun getCharacters(@Query("page") page: String): CharacterApiResponse

}

object RickAndMortyApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
