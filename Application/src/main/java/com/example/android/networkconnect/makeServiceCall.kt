package com.example.android.networkconnect

import com.example.android.networkconnect.api.ApiResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeServiceCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        //TODO: Using this to avid use connectivity
            ApiResponseStatus.Error(R.string.connection_error)
    } catch (e: Exception) {
        //TODO: Handle different types of exceptions
        ApiResponseStatus.Error(R.string.api_error_message)
    }
}