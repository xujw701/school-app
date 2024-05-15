package com.william.schoolapp.data.repository

import com.william.schoolapp.data.api.DataStoreApi
import com.william.schoolapp.data.model.Record
import com.william.schoolapp.data.safeApi
import javax.inject.Inject

interface SchoolRepository {

    suspend fun getSchools(
        limit: Int = 20,
        offset: Int = 0,
    ): List<Record>
}

internal class SchoolRepositoryImpl @Inject constructor(
    private val api: DataStoreApi
) : SchoolRepository {

    override suspend fun getSchools(
        limit: Int,
        offset: Int,
    ): List<Record> {
        val response = safeApi { api.search(limit, offset) }
        return response.component1()?.result?.records ?: emptyList()
    }
}