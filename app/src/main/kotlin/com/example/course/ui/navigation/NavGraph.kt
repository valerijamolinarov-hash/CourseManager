package com.example.course.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ImportExport
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.course.data.model.Course
import com.example.course.ui.screen.HomeScreen
import com.example.course.ui.screen.ImportScreen
import com.example.course.ui.screen.ManageScreen
import com.example.course.ui.screen.SettingsScreen
import com.example.course.ui.screen.TimetableScreen

sealed class Screen(val route: String, val label: String, val icon: androidx.compose.material.icons.Icon) {
    object Home : Screen("home", "首页", Icons.Filled.Home)
    object Timetable : Screen("timetable", "课程表", Icons.Filled.CalendarToday)
    object Manage : Screen("manage", "管理", Icons.Filled.Menu)
    object Import : Screen("import", "导入", Icons.Filled.ImportExport)
    object Settings : Screen("settings", "设置", Icons.Filled.Settings)
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    androidx.compose.foundation.layout.Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        androidx.compose.foundation.layout.Box(modifier = androidx.compose.ui.Modifier.padding(padding)) {
            NavigationHost(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Timetable,
        Screen.Manage,
        Screen.Import,
        Screen.Settings
    )

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = navController.currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onCourseClick = { course -> })
        }
        composable(Screen.Timetable.route) {
            TimetableScreen()
        }
        composable(Screen.Manage.route) {
            ManageScreen()
        }
        composable(Screen.Import.route) {
            ImportScreen()
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
