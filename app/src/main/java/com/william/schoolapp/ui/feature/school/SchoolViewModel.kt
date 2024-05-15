package com.william.schoolapp.ui.feature.school

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.william.schoolapp.data.repository.SchoolRepository
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
        val state = MutableStateFlow<SchoolViewState>(SchoolViewState())
    }

    val viewState: StateFlow<SchoolViewState> = view.state

    init {
        loadSchoolList()
    }

    private fun loadSchoolList() {
        viewModelScope.launch {
            val schools = schoolRepository.getSchools()
            view.state.update { it.copy(schoolList = schools) }
        }
    }
}