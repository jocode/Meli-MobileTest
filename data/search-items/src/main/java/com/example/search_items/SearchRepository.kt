package com.example.search_items

import com.jocode.model.search.Product
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchContent(query: String): Result<Flow<List<Product>>>
}