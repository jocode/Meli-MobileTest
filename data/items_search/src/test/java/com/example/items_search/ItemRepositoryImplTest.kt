package com.example.items_search

import com.example.items_search.remote.ItemApi
import com.example.items_search.remote.dto.ProductDescriptionResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ItemRepositoryImplTest {

    private lateinit var itemApi: ItemApi
    private lateinit var itemRepository: ItemRepositoryImpl

    @Before
    fun setUp() {
        itemApi = mockk()
        itemRepository = ItemRepositoryImpl(itemApi)
    }

    @Test
    fun `getProductDescription returns a success response`() = runBlocking {
        // Given
        val itemId = "itemId"

        coEvery { itemApi.getProductDescription(itemId) } returns Response.success(
            ProductDescriptionResponse(
                description = "plain_text"
            )
        )

        // When
        val result = itemRepository.getProductDescription(itemId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals("plain_text", result.getOrNull()?.description)

        coVerify { itemApi.getProductDescription(itemId) }
    }

    @Test
    fun `getProductDescription returns a failure response`() = runBlocking {
        // Given
        val itemId = "itemId"
        val error = Response.error<ProductDescriptionResponse>(
            400,
            "Bad Request".toResponseBody()
        )

        coEvery { itemApi.getProductDescription(itemId) } returns error

        // When
        val result = itemRepository.getProductDescription(itemId)

        // Then
        assertTrue(result.isFailure)

        coVerify { itemApi.getProductDescription(itemId) }
    }

}