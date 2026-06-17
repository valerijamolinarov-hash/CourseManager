package com.example.course.util

import androidx.compose.ui.graphics.Color

object ColorUtil {
    val courseColors = listOf(
        Color(0xFF6750A4), // 紫色
        Color(0xFF4A90D9), // 蓝色
        Color(0xFF4CAF50), // 绿色
        Color(0xFFFF9800), // 橙色
        Color(0xFFE91E63), // 粉色
        Color(0xFF00BCD4), // 青色
        Color(0xFFF44336)  // 红色
    )

    fun getColorByIndex(index: Int): Color {
        return courseColors[index % courseColors.size]
    }

    fun getColorIntByIndex(index: Int): Int {
        return courseColors[index % courseColors.size].hashCode()
    }

    fun intToColor(intValue: Int): Color {
        return Color(intValue)
    }
}
