package com.example.items_search

import com.jocode.model.search.Product
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchContent(query: String): Result<Flow<List<Product>>>
}