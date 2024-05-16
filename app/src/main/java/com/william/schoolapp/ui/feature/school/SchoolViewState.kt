package com.william.schoolapp.ui.feature.school
import com.william.schoolapp.data.model.SchoolRecord
import com.william.schoolapp.ui.utils.SchoolImagesProvider

data class SchoolViewState(
    val state: State = State.LOADING,
    val schools: List<SchoolUio> = emptyList(),
    val loadMore: LoadMore = LoadMore.HIDE,
    val refreshing: Boolean = false,
)  {
    enum class State { LOADING, ERROR, SUCCESS, EMPTY }
    enum class LoadMore { LOAD_MORE, LOADING, HIDE, LOAD_ERROR }
}

data class SchoolUio(
    val name: String,
    val status: String,
    val telephone: String,
    val imageUrl: String,
)

fun SchoolRecord.toUio() = SchoolUio(
    name = orgName,
    status = status,
    telephone = telephone,
    imageUrl = SchoolImagesProvider.getUrl(),
)

fun mapSchoolData(schoolRecord: List<SchoolRecord>) = schoolRecord.map { it.toUio() }