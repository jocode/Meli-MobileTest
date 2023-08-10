package com.example.items_search.remote

import com.example.items_search.remote.dto.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Search API declaration for Network API
 */
interface SearchApi {

    /**
     * Search products by query
     * @param query search query
     * @param limit limit of items
     * @param offset offset of items
     * @return [SearchResponse]
     */
    @GET("/sites/{siteId}/search")
    suspend fun searchProducts(
        @Path("siteId") siteId: String = "MCO",
        @Query("q") query: String,
        @Query("limit") limit: String = "10",
        @Query("offset") offset: Int = 0,
    ): Response<SearchResponse>

}