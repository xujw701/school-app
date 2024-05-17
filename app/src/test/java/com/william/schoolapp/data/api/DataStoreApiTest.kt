package com.william.schoolapp.data.api

import com.william.schoolapp.data.model.SearchResultResponse
import org.junit.Assert
import org.junit.Test
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.reflect.KFunction

internal class DataStoreApiTest {

    @Test
    fun `Verify search API`() {
        val function: KFunction<SearchResultResponse> = DataStoreApi::search
        Assert.assertTrue(
            function.annotations.contains(
                GET("datastore_search")
            )
        )
        Assert.assertTrue(
            function.parameters[1].annotations.contains(
                Query(
                    "limit",
                    encoded = false
                )
            )
        )
        Assert.assertTrue(
            function.parameters[2].annotations.contains(
                Query(
                    "offset",
                    encoded = false
                )
            )
        )
    }
}