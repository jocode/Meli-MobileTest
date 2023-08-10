package com.jocode.model.search

data class ProductDetail(
    override val id: String,
    override val title: String,
    override val price: Double,
    override val availableQuantity: Int,
    override val soldQuantity: Int,
    override val condition: String,
    override val thumbnail: String,
    override val currencyId: String,
    val description: String,
) : ProductBase

