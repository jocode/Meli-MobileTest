package com.jocode.domain

import com.example.search_items.SearchRepository
import com.jocode.model.search.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchItemUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(query: String): Flow<List<Product>> =
        searchRepository.getSearchContent(query)
}