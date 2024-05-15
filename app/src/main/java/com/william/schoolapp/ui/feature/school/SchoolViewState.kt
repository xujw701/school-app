package com.william.schoolapp.ui.feature.school
import com.william.schoolapp.data.model.Record

data class SchoolViewState(
    val schoolList: List<Record> = listOf()
)