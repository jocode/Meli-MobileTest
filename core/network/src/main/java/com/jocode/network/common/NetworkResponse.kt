package com.jocode.network.common

/**
 * Manages the possible states of a response.
 * It's used to handle the state of a network call.
 * - Success: The call was successful and the data is returned.
 * - Error: The call was successful but the data is not returned.
 * - Exception: The call was not successful and an exception is returned.
 */
sealed class NetworkResponse<T> {
    class Success<T>(val data: T) : NetworkResponse<T>()
    class Error<T : Any>(val code: Int, val message: String?) : NetworkResponse<T>()
    class Failure<T : Any>(val exception: Exception) : NetworkResponse<T>()

    inline fun onSuccess(onSuccess: (value: T) -> Unit): NetworkResponse<T> {
        if (this is Success) {
            onSuccess(data)
        }
        return this
    }

    inline fun onError(onError: (code: Int, message: String?) -> Unit): NetworkResponse<T> {
        if (this is Error) {
            onError(code, message)
        }
        return this
    }

    inline fun onFailure(onException: (exception: Exception) -> Unit): NetworkResponse<T> {
        if (this is Failure) {
            onException(exception)
        }
        return this
    }
}


/**
 * This function folds the possible states of a response as a lambda.
 */
inline fun <R, T> NetworkResponse<T>.fold(
    onSuccess: (value: T) -> R,
    onError: (code: Int, message: String?) -> R,
    onException: (exception: Exception) -> R,
): R = when (this) {
    is NetworkResponse.Success -> onSuccess(data)
    is NetworkResponse.Error -> onError(code, message)
    is NetworkResponse.Failure -> onException(exception)
}


fun <T> NetworkResponse<T>.getSuccess() = when (this) {
    is NetworkResponse.Success -> this.data
    else -> null
}

fun <T> NetworkResponse<T>.getException() = when (this) {
    is NetworkResponse.Failure -> exception
    else -> null
}

fun <T> NetworkResponse<T>.getHttpErrorCode() = when (this) {
    is NetworkResponse.Error -> code
    else -> null
}