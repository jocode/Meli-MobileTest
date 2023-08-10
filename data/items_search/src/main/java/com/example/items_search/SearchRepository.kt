package com.example.items_search

import androidx.paging.PagingData
import com.jocode.model.search.Product
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchPagingSource(query: String): Flow<PagingData<Product>>
}