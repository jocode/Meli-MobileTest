package com.jocode.network.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Safely processes a network response and returns a [NetworkResponse] object.
 * This function is designed to handle network responses and provide appropriate
 * success or error outcomes.
 */
suspend fun <T : Any> makeSafeRequest(
    execute: suspend () -> Response<T>,
): NetworkResponse<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResponse.Success(body)
            } else {
                NetworkResponse.Error(code = response.code(), message = response.message())
            }
        } catch (e: Exception) {
            NetworkResponse.Failure(e)
        }
    }
}