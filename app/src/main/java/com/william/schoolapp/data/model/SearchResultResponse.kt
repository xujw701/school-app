package com.william.schoolapp.data.model

import com.google.gson.annotations.SerializedName

data class SearchResultResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("result")
    val data: SearchResult,
)

data class SearchResult(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("records")
    val schoolRecord: List<SchoolRecord>,
    @SerializedName("total")
    val total: Int,
)
