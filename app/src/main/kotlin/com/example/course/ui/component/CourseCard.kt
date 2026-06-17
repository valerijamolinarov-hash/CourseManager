package com.example.course.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.course.data.model.Course

@Composable
fun CourseCard(
    course: Course,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    val courseColor = try {
        Color(android.graphics.Color.parseColor(course.color))
    } catch (e: Exception) {
        Color(0xFF6750A4)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = course.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = courseColor
            )

            Text(
                text = "地点: ${course.location}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                text = "时间: ${course.startTime} - ${course.endTime}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                text = "教师: ${course.teacher}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                text = "第${course.startWeek}-${course.endWeek}周",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}