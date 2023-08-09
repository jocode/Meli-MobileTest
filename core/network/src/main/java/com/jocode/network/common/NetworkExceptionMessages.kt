package com.jocode.network.common

import com.jocode.meli_mobiletest.R
import com.jocode.network.common.HttpErrors.getHttpErrorMessage
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

fun Throwable.getErrorMessage(): Int = when (this) {
    is UnknownHostException -> R.string.unknown_host_exception
    is IOException -> R.string.io_exception
    is HttpException -> getHttpErrorMessage(code())
    else -> R.string.unknown_error
}