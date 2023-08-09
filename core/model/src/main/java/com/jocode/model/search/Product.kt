package com.jocode.model.search

data class Product(
    override val id: String,
    override val title: String,
    override val price: Double,
    override val availableQuantity: Int,
    override val soldQuantity: Int,
    override val condition: String,
    override val thumbnail: String,
    override val currencyId: String,
) : ProductBase

/**
 * Base interface for product-related domain entities.
 * This interface defines common properties that products may have.
 */
internal interface ProductBase {
    val id: String
    val title: String
    val price: Double
    val availableQuantity: Int
    val soldQuantity: Int
    val condition: String
    val thumbnail: String
    val currencyId: String
}
