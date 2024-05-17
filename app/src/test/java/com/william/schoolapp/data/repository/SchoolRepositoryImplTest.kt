package com.william.schoolapp.data.repository

import com.william.schoolapp.data.Err
import com.william.schoolapp.data.Ok
import com.william.schoolapp.data.api.DataStoreApi
import com.william.schoolapp.data.model.SchoolRecord
import com.william.schoolapp.data.model.SearchResult
import com.william.schoolapp.data.model.SearchResultResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class SchoolRepositoryImplTest {

    private val api = mockk<DataStoreApi>()
    private val repository = SchoolRepositoryImpl(api)

    private val mockSchools = listOf(
        SchoolRecord(
            2872,
            "Mahinawa Specialist School and Resource Centre",
            "04-2384314",
            "reception@mahinawa.school.nz",
            "http://www.schoolground.co.nz/kapimana",
            "Specialist School",
            "Open",
            "1b Takapuwahia Drive ",
            "",
            "Porirua"
        ),
        // Add more sample records if needed
    )

    @Test
    fun `Given getSchools invoked Then return SearchResultResponse`() = runTest {
        // Mock the response from the API
        val expectedResult = SearchResultResponse(
            success = true,
            data = SearchResult(1, 0, mockSchools, 2554)
        )
        coEvery { api.search(any(), any()) } returns expectedResult

        // Call the method being tested
        val result = repository.getSchools(1, 0)

        // Verify the result
        assertEquals(Ok(expectedResult), result)
    }

    @Test
    fun `Given getSchools invoked and API throws an exception Then return Result Error`() = runTest {
        // Mock the API to throw an exception
        val expectedException = Exception("API call failed")
        coEvery { api.search(any(), any()) } throws expectedException

        // Call the method being tested
        val result = repository.getSchools()

        // Verify the result
        assertEquals(Err(expectedException), result)
    }
}