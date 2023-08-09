package com.example.search_items

import com.jocode.model.search.Product
import com.jocode.network.common.fold
import com.jocode.network.common.makeSafeRequest
import com.jocode.network.retrofit.SearchApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
) : SearchRepository {
    override suspend fun getSearchContent(query: String): Flow<List<Product>> {
        val response = makeSafeRequest { searchApi.searchProducts(query) }
        return response.fold(
            onSuccess = { data ->
                val items = data.results.map {
                    it.toDomain()
                }
                flowOf(items)
            },
            onError = { code, message ->
                flowOf(emptyList())
            },
            onException = {
                flowOf(emptyList())
            }
        )
    }

}