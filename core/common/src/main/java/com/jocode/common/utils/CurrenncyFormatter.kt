package com.jocode.common.utils

import java.text.NumberFormat
import java.util.Locale

fun Number.toLocalCurrency(): String {
    return NumberFormat.getCurrencyInstance(
        Locale.getDefault()
    ).apply {
        maximumFractionDigits = 0
    }.format(this) ?: "0"
}