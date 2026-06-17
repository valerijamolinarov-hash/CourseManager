package com.example.course.data.repository

import com.example.course.data.local.CourseDao
import com.example.course.data.model.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val courseDao: CourseDao
) {
    fun getAllCourses(semester: String): Flow<List<Course>> {
        return courseDao.getAllCourses(semester)
    }

    fun getCoursesByDay(semester: String, dayOfWeek: Int): Flow<List<Course>> {
        return courseDao.getCoursesByDay(semester, dayOfWeek)
    }

    suspend fun getCourseById(id: Long): Course? {
        return courseDao.getCourseById(id)
    }

    suspend fun insertCourse(course: Course): Long {
        return courseDao.insertCourse(course)
    }

    suspend fun insertCourses(courses: List<Course>) {
        courseDao.insertCourses(courses)
    }

    suspend fun updateCourse(course: Course) {
        courseDao.updateCourse(course)
    }

    suspend fun deleteCourse(course: Course) {
        courseDao.deleteCourse(course)
    }

    suspend fun deleteAllCourses(semester: String) {
        courseDao.deleteAllCourses(semester)
    }

    fun getAllSemesters(): Flow<List<String>> {
        return courseDao.getAllSemesters()
    }
}
