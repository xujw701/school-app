package com.william.schoolapp.data.api

import com.william.schoolapp.data.model.SearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DataStoreApi {
    @GET("datastore_search")
    suspend fun search(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): SearchResultResponse
}