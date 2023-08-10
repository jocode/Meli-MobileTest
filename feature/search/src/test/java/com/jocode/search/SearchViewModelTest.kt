package com.jocode.search

import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jocode.domain.GetSearchItemUseCase
import com.jocode.model.search.Product
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    @MockK
    private lateinit var searchUseCase: GetSearchItemUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(searchUseCase)
    }

    @Test
    fun `test search functionality`() = runBlocking {
        // Mocking dependencies
        val searchQuery = "example query"

        val getSearchItemUseCase = mockk<GetSearchItemUseCase>()
        coEvery { getSearchItemUseCase(searchQuery) } returns flowOf(PagingData.empty())

        val viewModel = SearchViewModel(getSearchItemUseCase)

        // Initial state
        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(SearchResultUiState.EmptyQuery)
        }

        // Update search query
        viewModel.onSearchQuerySubmit(searchQuery)

        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(SearchResultUiState.Loaded)
        }
    }

    @Test
    fun `test searchChange listener to save the state`() = runBlocking {
        val searchQuery = "example"
        viewModel.onSearchQueryChanged(searchQuery)
        assertThat(viewModel.searchQuery.value).isEqualTo(searchQuery)

        viewModel.onSearchQueryChanged("")
        assertThat(viewModel.searchQuery.value).isEqualTo("")

        viewModel.onSearchQueryChanged("another example")
        assertThat(viewModel.searchQuery.value).isEqualTo("another example")
    }

    @Test
    fun `test onSearch submit then return searchResults successfully`() = runBlocking {
        val searchQuery = "example query"

        val getSearchItemUseCase = mockk<GetSearchItemUseCase>()
        coEvery { getSearchItemUseCase(searchQuery) } returns flowOf(PagingData.empty())

        val viewModel = SearchViewModel(getSearchItemUseCase)

        viewModel.onSearchQuerySubmit(searchQuery)

        viewModel.searchResults.value = flowOf(
            PagingData.from(listOf(product))
        )

        viewModel.searchResults.asStateFlow().value.collect {
            assertThat(it).isInstanceOf(PagingData::class.java)
            it.map { item ->
                assertThat(item).isEqualTo(product)
            }
        }
    }

    private val product = Product(
        id = "1",
        title = "title",
        price = 100.0,
        availableQuantity = 1,
        currencyId = "ARS",
        thumbnail = "thumbnail",
        soldQuantity = 1,
        condition = "new"
    )

}