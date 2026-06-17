package com.example.course.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course.data.model.Course
import com.example.course.data.repository.CourseRepository
import com.example.course.util.DateUtil
import dagger.hilt.android.lifecycle.ViewModelInject
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    val todayCourses = mutableStateListOf<Course>()
    val allCourses = mutableStateListOf<Course>()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            courseRepository.getAllCourses("2025-2026-2").collect { courses ->
                allCourses.clear()
                allCourses.addAll(courses)

                val today = DateUtil.getCurrentDayOfWeek()
                todayCourses.clear()
                todayCourses.addAll(courses.filter { it.dayOfWeek == today })
            }
        }
    }
}
