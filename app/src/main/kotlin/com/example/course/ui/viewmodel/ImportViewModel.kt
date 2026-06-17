package com.example.course.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course.data.model.Course
import com.example.course.data.remote.CourseService
import com.example.course.data.remote.LoginService
import com.example.course.data.repository.CourseRepository
import com.example.course.data.repository.UserRepository
import com.example.course.util.ColorUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class ImportViewModel @Inject constructor(
    private val loginService: LoginService,
    private val courseService: CourseService,
    private val courseRepository: CourseRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val successMessage = mutableStateOf<String?>(null)

    fun importCourses(username: String, password: String, semester: String) {
        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.value = "请输入学号和密码"
            return
        }

        isLoading.value = true
        errorMessage.value = null
        successMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginResult = loginService.login(username, password)

                when (loginResult) {
                    is LoginService.LoginResult.Success -> {
                        val courseResult = courseService.getCourseList(loginResult.cookies)

                        when (courseResult) {
                            is CourseService.CourseFetchResult.Success -> {
                                val courses = parseCourseHtml(courseResult.htmlContent, semester)
                                courseRepository.deleteAllCourses(semester)
                                courseRepository.insertCourses(courses)
                                userRepository.saveCredential(username, password, semester)

                                successMessage.value = "成功导入 ${courses.size} 门课程"
                            }
                            is CourseService.CourseFetchResult.Error -> {
                                errorMessage.value = courseResult.message
                            }
                        }
                    }
                    is LoginService.LoginResult.Error -> {
                        errorMessage.value = loginResult.message
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = "导入失败: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    private fun parseCourseHtml(htmlContent: String, semester: String): List<Course> {
        val courses = mutableListOf<Course>()
        var colorIndex = 0

        return courses
    }
}
