package com.jocode.network.searchs_items

import com.jocode.model.search.Product
import com.squareup.moshi.Json

/**
 * Search response from Network API
 */
data class SearchResponse(
    val results: List<ProductDTO>,
)

data class ProductDTO(
    val id: String,
    val title: String,
    val price: Double,
    @field:Json(name = "available_quantity")
    val availableQuantity: Int,
    @field:Json(name = "sold_quantity")
    val soldQuantity: Int,
    val condition: String,
    val thumbnail: String,
    @field:Json(name = "currency_id")
    val currencyId: String,
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            availableQuantity = availableQuantity,
            soldQuantity = soldQuantity,
            condition = condition,
            thumbnail = thumbnail,
            currencyId = currencyId
        )
    }
}