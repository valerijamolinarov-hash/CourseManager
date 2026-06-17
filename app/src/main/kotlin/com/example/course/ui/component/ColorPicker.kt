package com.example.course.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.course.util.ColorUtil

@Composable
fun ColorPickerDialog(
    selectedColor: Int,
    onColorSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("选择课程颜色") },
        text = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ColorUtil.courseColors.forEachIndexed { index, colorString ->
                    val colorInt = ColorUtil.getColorIntByIndex(index)
                    val isSelected = selectedColor == colorInt
                    ColorOption(
                        colorInt = colorInt,
                        isSelected = isSelected,
                        onClick = {
                            onColorSelected(colorInt)
                            onDismiss()
                        }
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("确定")
            }
        }
    )
}

@Composable
fun ColorOption(
    colorInt: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = Color(colorInt)
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .border(
                width = if (isSelected) 3.dp else 0.dp,
                color = Color.Black
            )
            .clickable { onClick() }
    ) {}
}