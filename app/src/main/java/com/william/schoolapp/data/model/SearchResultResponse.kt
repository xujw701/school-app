package com.william.schoolapp.data.model

import com.google.gson.annotations.SerializedName

data class SearchResultResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("result")
    val result: SearchResult,
)

data class SearchResult(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("records")
    val records: List<Record>,
)
