package com.example.course.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val teacher: String,
    val location: String,
    val dayOfWeek: Int,
    val startWeek: Int,
    val endWeek: Int,
    val startTime: String,
    val endTime: String,
    val startPeriod: Int,
    val endPeriod: Int,
    val color: String,
    val semester: String
)