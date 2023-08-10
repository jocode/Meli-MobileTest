package com.jocode.domain

import com.example.items_search.SearchRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSearchItemUseCaseTest {

    private lateinit var searchRepository: SearchRepository
    private lateinit var useCase: GetSearchItemUseCase

    @Before
    fun setUp() {
        searchRepository = mockk()
        useCase = GetSearchItemUseCase(searchRepository)
    }

    @Test
    fun `when invoke useCase check the repository call`() = runBlocking {
        // Given
        val query = "query"


        coEvery { searchRepository.getSearchPagingSource(query) } returns mockk()

        // When
        val result = useCase(query)

        // Then
        println(result)
        coVerify { searchRepository.getSearchPagingSource(query) }
    }

    @Test
    fun `when not invoke useCase check the repository call`() = runBlocking {
        // Given
        val query = "query"
        coEvery { searchRepository.getSearchPagingSource(query) } returns mockk()

        // Repository is not called

        coVerify(exactly = 0) { searchRepository.getSearchPagingSource(query) }
    }

}