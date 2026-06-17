package com.example.course.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val courseName: String,
    val teacherName: String,
    val classroom: String,
    val dayOfWeek: Int,
    val startWeek: Int,
    val endWeek: Int,
    val startTime: String,
    val endTime: String,
    val startSection: Int,
    val endSection: Int,
    val color: Int,
    val semester: String
)
