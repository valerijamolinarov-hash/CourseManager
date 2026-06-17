package com.example.course.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course.data.model.Course
import com.example.course.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    val allCourses = mutableStateListOf<Course>()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            courseRepository.getAllCourses("2025-2026-2").collect { courses ->
                allCourses.clear()
                allCourses.addAll(courses)
            }
        }
    }
}
