package com.example.items_search

import androidx.paging.PagingSource
import com.example.items_search.remote.SearchApi
import com.example.items_search.remote.SearchPagingSource
import com.jocode.model.search.Product
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {

    private lateinit var searchApi: SearchApi
    private lateinit var searchRepository: SearchRepositoryImpl

    @Before
    fun setUp() {
        searchApi = mockk()
        searchRepository = SearchRepositoryImpl(searchApi)
    }

    @Test
    fun `when getSearchPagingSource verify the api call`(): Unit = runBlocking {
        // Given
        val searchPagingSource: SearchPagingSource = mockk()
        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = null
        )

        // When
        searchRepository.getSearchPagingSource("query")

        // Then
        coEvery { searchApi.searchProducts(query = "query", offset = 1) }

    }

    @Test
    fun `getSearchPagingSource returns empty PagingData`(): Unit = runBlocking {
        // Given
        val searchPagingSource: SearchPagingSource = mockk()
        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = null
        )

        // When
        searchRepository.getSearchPagingSource("query")
        val loadResult = searchPagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))

        // Then
        assertEquals(
            PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            ),
            loadResult
        )
    }

    @Test
    fun `getSearchPagingSource returns PagingData with 1 item`(): Unit = runBlocking {
        // Given
        val searchPagingSource: SearchPagingSource = mockk()
        val product = Product(
            id = "id",
            title = "title",
            thumbnail = "thumbnail",
            price = 10.0,
            availableQuantity = 1,
            soldQuantity = 2,
            condition = "condition",
            currencyId = "currencyId",
        )

        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Page(
            data = listOf(product),
            prevKey = null,
            nextKey = null
        )

        // When
        searchRepository.getSearchPagingSource("query")
        val loadResult = searchPagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))

        // Then
        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(product),
                prevKey = null,
                nextKey = null
            ),
            loadResult
        )
    }

    @Test
    fun `getSearchPagingSource return error value`(): Unit = runBlocking {
        // Given
        val searchPagingSource: SearchPagingSource = mockk()
        val exception = Exception("error")

        coEvery { searchPagingSource.load(any()) } returns PagingSource.LoadResult.Error(exception)

        // When
        searchRepository.getSearchPagingSource("query")
        val loadResult = searchPagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))

        // Then
        assertEquals(
            PagingSource.LoadResult.Error<Any, Any>(throwable = exception),
            loadResult
        )
    }

}