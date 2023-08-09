package com.example.items_search.remote.dto

import com.jocode.model.search.ProductDescription
import com.squareup.moshi.Json

data class ProductDescriptionResponse(
    @field:Json(name = "plain_text") val description: String,
) {
    fun toProductDescription(): ProductDescription = ProductDescription(description)
}
