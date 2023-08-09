package com.jocode.model.search

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val condition: String,
    val thumbnail: String,
    val currencyId: String,
)
