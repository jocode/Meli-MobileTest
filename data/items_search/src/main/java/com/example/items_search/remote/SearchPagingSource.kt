package com.example.items_search.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jocode.model.search.Product
import com.jocode.network.common.fold
import com.jocode.network.common.makeSafeRequest

class SearchPagingSource(
    private val searchApi: SearchApi,
    private val query: String,
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        try {
            val page = params.key ?: 1
            val response =
                makeSafeRequest { searchApi.searchProducts(query = query, offset = page) }

            response.fold(
                onSuccess = { data ->
                    val items = data.results.map {
                        it.toDomain()
                    }
                    return LoadResult.Page(
                        data = items,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (items.isEmpty()) null else page.plus(1)
                    )
                },
                onError = { _, message ->
                    return LoadResult.Error(Exception(message))
                },
                onException = {
                    return LoadResult.Error(it)
                }
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}