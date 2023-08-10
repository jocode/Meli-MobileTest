package com.example.items_search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.items_search.remote.SearchApi
import com.example.items_search.remote.SearchPagingSource
import com.jocode.model.search.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
) : SearchRepository {

    override fun getSearchPagingSource(query: String): Flow<PagingData<Product>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { SearchPagingSource(searchApi, query) }
        ).flow

}