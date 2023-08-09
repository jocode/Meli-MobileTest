package com.jocode.domain

import com.example.search_items.SearchRepository
import javax.inject.Inject

class GetSearchItemUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    suspend operator fun invoke(query: String) =
        searchRepository.getSearchContent(query)
}