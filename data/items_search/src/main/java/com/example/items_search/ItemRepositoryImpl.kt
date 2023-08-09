package com.example.items_search

import com.example.items_search.remote.ItemApi
import com.jocode.model.search.ProductDescription
import com.jocode.network.common.fold
import com.jocode.network.common.makeSafeRequest
import javax.inject.Inject

internal class ItemRepositoryImpl @Inject constructor(
    private val itemApi: ItemApi,
) : ItemRepository {
    override suspend fun getProductDescription(itemId: String): Result<ProductDescription> {
        val response = itemApi.getProductDescription(itemId).makeSafeRequest()

        return response.fold(
            onSuccess = { data ->
                Result.success(data.toProductDescription())
            },
            onError = { _, message ->
                Result.failure(Exception(message))
            },
            onException = {
                Result.failure(it)
            }
        )
    }


}