package com.example.course.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course.data.model.Course
import com.example.course.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.ViewModelScoped
import dagger.hilt.android.lifecycle.ViewModelInject
import kotlinx.coroutines.launch

@ViewModelScoped
class ManageViewModel @ViewModelInject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    val allCourses = mutableStateListOf<Course>()
    val showAddOrEditDialog = mutableStateOf(false)
    val editingCourse = mutableStateOf<Course?>(null)
    val showDeleteDialog = mutableStateOf(false)
    val deletingCourse = mutableStateOf<Course?>(null)

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

    fun showAddDialog() {
        editingCourse.value = null
        showAddOrEditDialog.value = true
    }

    fun showEditDialog(course: Course) {
        editingCourse.value = course
        showAddOrEditDialog.value = true
    }

    fun hideDialog() {
        showAddOrEditDialog.value = false
        editingCourse.value = null
    }

    fun showDeleteDialog(course: Course) {
        deletingCourse.value = course
        showDeleteDialog.value = true
    }

    fun hideDeleteDialog() {
        showDeleteDialog.value = false
        deletingCourse.value = null
    }

    fun addCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.insertCourse(course)
        }
    }

    fun updateCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.updateCourse(course)
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.deleteCourse(course)
        }
    }
}
