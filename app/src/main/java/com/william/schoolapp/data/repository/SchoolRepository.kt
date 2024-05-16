package com.william.schoolapp.data.repository

import com.william.schoolapp.data.api.DataStoreApi
import com.william.schoolapp.data.Result
import com.william.schoolapp.data.model.SearchResultResponse
import com.william.schoolapp.data.safeApi
import javax.inject.Inject

interface SchoolRepository {

    suspend fun getSchools(
        limit: Int = 20,
        offset: Int = 0,
    ): Result<SearchResultResponse, Throwable>
}

internal class SchoolRepositoryImpl @Inject constructor(
    private val api: DataStoreApi
) : SchoolRepository {

    override suspend fun getSchools(
        limit: Int,
        offset: Int,
    ): Result<SearchResultResponse, Throwable> {
        return safeApi { api.search(limit, offset) }
    }
}