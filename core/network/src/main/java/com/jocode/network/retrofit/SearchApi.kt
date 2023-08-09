package com.jocode.network.retrofit

import com.jocode.network.searchs_items.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
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
    @GET("search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("limit") limit: String = "10",
        @Query("offset") offset: Int = 0,
    ): Response<SearchResponse>

}