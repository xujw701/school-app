package com.william.schoolapp.ui.feature.school
import com.william.schoolapp.data.model.SchoolRecord

data class SchoolViewState(
    val schoolList: List<SchoolRecord> = listOf()
)