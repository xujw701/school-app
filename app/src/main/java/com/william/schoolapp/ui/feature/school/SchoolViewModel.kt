package com.william.schoolapp.ui.feature.school

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.william.schoolapp.data.fold
import com.william.schoolapp.data.model.SchoolRecord
import com.william.schoolapp.data.repository.SchoolRepository
import com.william.schoolapp.ui.feature.school.SchoolViewState.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SchoolViewModel @Inject constructor(
    private val schoolRepository: SchoolRepository,
) : ViewModel() {

    private val view = object {
        val state = MutableStateFlow(SchoolViewState())
    }

    val viewState: StateFlow<SchoolViewState> = view.state

    private var schools: List<SchoolRecord> = emptyList()

    init {
        viewModelScope.launch {
            fetchSchools()
        }
    }

    suspend fun refresh() {
        fetchSchools()
    }

    fun loadMore() {
        view.state.update { it.copy(loadMore = SchoolViewState.LoadMore.LOADING) }
        viewModelScope.launch {
            schoolRepository.getSchools(
                offset = schools.size,
            ).fold(
                success = { result ->
                    schools = schools.plus(result.data.schoolRecord)
                    view.state.update { currentState ->
                        currentState.copy(
                            schools = mapSchoolData(schools),
                            loadMore = handleLoadMore(schools.count() < result.data.total)
                        )
                    }
                },
                failure = {
                    view.state.update { currentState -> currentState.copy(loadMore = SchoolViewState.LoadMore.LOAD_ERROR) }
                }
            )
        }
    }

    private suspend fun fetchSchools() {
        schoolRepository.getSchools().fold(
            success = { result ->
                view.state.update { currentState ->
                    schools = result.data.schoolRecord
                    if (schools.isEmpty()) {
                        currentState.copy(
                            state = State.EMPTY
                        )
                    } else {
                        currentState.copy(
                            state = State.SUCCESS,
                            schools = mapSchoolData(schools),
                            loadMore = handleLoadMore(schools.count() < result.data.total)
                        )
                    }
                }
            },
            failure = {
                view.state.update { currentState -> currentState.copy(state = State.ERROR) }
            }
        )
    }

    private fun handleLoadMore(hasMore: Boolean) = if (hasMore) SchoolViewState.LoadMore.LOAD_MORE else SchoolViewState.LoadMore.HIDE

}