package com.example.items_search.remote

import com.example.items_search.remote.dto.ProductDescriptionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemApi {

    @GET("/items/{id}/description")
    suspend fun getProductDescription(
        @Path("id") itemId: String,
    ): Response<ProductDescriptionResponse>

}