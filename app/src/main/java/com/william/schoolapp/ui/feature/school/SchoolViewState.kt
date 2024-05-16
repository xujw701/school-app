package com.william.schoolapp.ui.feature.school
import com.william.schoolapp.data.model.SchoolRecord

data class SchoolViewState(
    val state: State = State.LOADING,
    val schools: List<SchoolRecord> = emptyList(),
    val loadMore: LoadMore = LoadMore.HIDE,
    val refreshing: Boolean = false,
)  {
    enum class State { LOADING, ERROR, SUCCESS, EMPTY }
    enum class LoadMore { LOAD_MORE, LOADING, HIDE, LOAD_ERROR }
}