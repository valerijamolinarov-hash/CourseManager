package com.example.course.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.course.data.model.Course
import com.example.course.data.model.UserCredential

@Database(
    entities = [Course::class, UserCredential::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun userCredentialDao(): UserCredentialDao
}
