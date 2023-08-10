package com.jocode.domain

import com.example.items_search.ItemRepository
import com.jocode.model.search.ProductDescription
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetItemDescriptionUseCaseTest {

    private lateinit var itemRepository: ItemRepository

    lateinit var useCase: GetItemDescriptionUseCase

    @Before
    fun setUp() {
        itemRepository = mockk()
        useCase = GetItemDescriptionUseCase(itemRepository)
    }

    @Test
    fun `when invoke useCase return a success response`() = runBlocking {
        // Given
        val itemId = "itemId"
        coEvery { itemRepository.getProductDescription(itemId) } returns Result.success(
            ProductDescription(
                description = "plain_text"
            )
        )

        // When
        val result = useCase(itemId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals("plain_text", result.getOrNull()?.description)
        coVerify { itemRepository.getProductDescription(itemId) }
    }

    @Test
    fun `when invoke useCase return a failure response`() = runBlocking {
        // Given
        val itemId = "itemId"
        val error = Result.failure<ProductDescription>(
            Throwable("Bad Request")
        )

        coEvery { itemRepository.getProductDescription(itemId) } returns error

        // When
        val result = useCase(itemId)

        // Then
        assertFalse(result.isSuccess)
        assertEquals("Bad Request", result.exceptionOrNull()?.message)
        coVerify { itemRepository.getProductDescription(itemId) }
    }

}