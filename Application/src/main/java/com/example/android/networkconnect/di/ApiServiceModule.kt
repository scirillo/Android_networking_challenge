package com.example.android.networkconnect.di

import com.example.android.networkconnect.BASE_URL
import com.example.android.networkconnect.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun provideApiService(retrofit: Retrofit) =  retrofit.create(ApiService::class.java)

    @Provides
    fun provideRetrofit(okkHttpClient: OkHttpClient) = Retrofit.Builder().client(okkHttpClient).baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create()).build()

    @Provides
    fun provideHttpClient() =  OkHttpClient.Builder().build()
}