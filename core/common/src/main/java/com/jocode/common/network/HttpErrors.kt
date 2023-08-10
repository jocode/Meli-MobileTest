package com.jocode.common.network

import com.jocode.meli_mobiletest.R

object HttpErrors {
    fun getHttpErrorMessage(errorCode: Int): Int = when (errorCode) {
        503 -> R.string.service_unavailable_error_message
        500 -> R.string.internal_server_error_message
        404 -> R.string.not_found_error_message
        400 -> R.string.invalid_request_error_message
        401 -> R.string.unauthorized_error_message
        else -> R.string.unknown_error
    }
}