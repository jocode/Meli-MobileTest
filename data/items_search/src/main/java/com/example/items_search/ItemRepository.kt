package com.example.items_search

import com.jocode.model.search.ProductDescription

interface ItemRepository {
    suspend fun getProductDescription(itemId: String): Result<ProductDescription>
}