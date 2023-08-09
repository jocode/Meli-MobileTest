package com.example.items_search

import com.example.items_search.remote.SearchApi
import com.jocode.model.search.Product
import com.jocode.network.common.fold
import com.jocode.network.common.makeSafeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
) : SearchRepository {
    override suspend fun getSearchContent(query: String): Result<Flow<List<Product>>> {
        val response = makeSafeRequest { searchApi.searchProducts(query) }
        return response.fold(
            onSuccess = { data ->
                val items = data.results.map {
                    it.toDomain()
                }
                Result.success(flowOf(items))
            },
            onError = { code, message ->
                Result.failure(Exception("Error $code: $message"))
            },
            onException = {
                Result.failure(it)
            }
        )
    }

}