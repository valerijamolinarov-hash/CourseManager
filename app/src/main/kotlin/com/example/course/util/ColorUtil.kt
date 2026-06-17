package com.example.course.util

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

object ColorUtil {
    val courseColors = listOf(
        "#6750A4", // 紫色
        "#4A90D9", // 蓝色
        "#4CAF50", // 绿色
        "#FF9800", // 橙色
        "#E91E63", // 粉色
        "#00BCD4", // 青色
        "#F44336"  // 红色
    )

    fun getColorByIndex(index: Int): Color {
        return try {
            Color(AndroidColor.parseColor(courseColors[index % courseColors.size]))
        } catch (e: Exception) {
            Color(0xFF6750A4)
        }
    }

    fun getColorStringByIndex(index: Int): String {
        return courseColors[index % courseColors.size]
    }

    fun stringToColor(colorString: String): Color {
        return try {
            Color(AndroidColor.parseColor(colorString))
        } catch (e: Exception) {
            Color(0xFF6750A4)
        }
    }

    fun intToColor(intValue: Int): Color {
        return Color(intValue)
    }
}