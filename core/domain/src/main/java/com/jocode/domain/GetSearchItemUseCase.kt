package com.jocode.domain

import com.example.items_search.SearchRepository
import javax.inject.Inject

class GetSearchItemUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {
    operator fun invoke(query: String) = searchRepository.getSearchPagingSource(query)

}