package com.example.course.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtil {
    fun getCurrentDayOfWeek(): Int {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return if (dayOfWeek == Calendar.SUNDAY) 7 else dayOfWeek
    }

    fun getCurrentWeekOfSemester(startDate: String = "2025-09-01"): Int {
        return try {
            val start = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(startDate)
            val current = Date()
            val diff = current.time - start.time
            val days = diff / (1000 * 60 * 60 * 24)
            (days / 7).toInt() + 1
        } catch (e: Exception) {
            1
        }
    }

    fun formatDate(date: Date): String {
        return SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).format(date)
    }

    fun getDayName(dayOfWeek: Int): String {
        val days = arrayOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")
        return days.getOrElse(dayOfWeek - 1) { "未知" }
    }

    fun getCurrentTime(): String {
        return SimpleDateFormat("HH:mm", Locale.CHINA).format(Date())
    }

    fun isCourseActive(course: com.example.course.data.model.Course): Boolean {
        val currentWeek = getCurrentWeekOfSemester()
        val currentDay = getCurrentDayOfWeek()
        val currentTime = getCurrentTime()

        if (currentDay != course.dayOfWeek) return false
        if (currentWeek < course.startWeek || currentWeek > course.endWeek) return false
        return currentTime >= course.startTime && currentTime < course.endTime
    }
}
