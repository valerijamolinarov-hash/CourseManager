package com.example.course.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.course.data.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses WHERE semester = :semester ORDER BY dayOfWeek, startSection")
    fun getAllCourses(semester: String): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE semester = :semester AND dayOfWeek = :dayOfWeek ORDER BY startSection")
    fun getCoursesByDay(semester: String, dayOfWeek: Int): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE id = :id")
    suspend fun getCourseById(id: Long): Course?

    @Insert
    suspend fun insertCourse(course: Course): Long

    @Insert
    suspend fun insertCourses(courses: List<Course>)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Query("DELETE FROM courses WHERE semester = :semester")
    suspend fun deleteAllCourses(semester: String)

    @Query("SELECT DISTINCT semester FROM courses ORDER BY semester DESC")
    fun getAllSemesters(): Flow<List<String>>
}
