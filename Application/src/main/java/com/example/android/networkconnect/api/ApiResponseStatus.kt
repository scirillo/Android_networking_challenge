package com.example.android.networkconnect.api

sealed class ApiResponseStatus<T> {
    class Success<T>(val data: T) : ApiResponseStatus<T>()
    class Loading<T> : ApiResponseStatus<T>()
    class Error<T>(val errorId: Int) : ApiResponseStatus<T>()
}