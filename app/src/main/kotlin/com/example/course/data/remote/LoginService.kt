package com.example.course.data.remote

import android.util.Base64
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.Response

class LoginService {
    private val client = ApiClient.okHttpClient

    suspend fun login(username: String, password: String): LoginResult {
        return try {
            val encodedPassword = Base64.encodeToString(password.toByteArray(), Base64.DEFAULT)
                .trim()

            val formBody = FormBody.Builder()
                .add("userNo", username)
                .add("pwd", encodedPassword)
                .add("captchaData", "")
                .add("codeVal", "")
                .build()

            val request = Request.Builder()
                .url("http://jwyd.dlnu.edu.cn/njwhd/login")
                .post(formBody)
                .addHeader("Origin", "http://jwyd.dlnu.edu.cn")
                .addHeader("Referer", "http://jwyd.dlnu.edu.cn/sjd/")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build()

            val response = client.newCall(request).execute()
            handleLoginResponse(response)
        } catch (e: Exception) {
            LoginResult.Error("网络连接失败: ${e.message}")
        }
    }

    private fun handleLoginResponse(response: Response): LoginResult {
        return if (response.isSuccessful) {
            val cookies = response.headers("Set-Cookie")
            if (cookies.isNotEmpty()) {
                LoginResult.Success(cookies)
            } else {
                LoginResult.Error("登录失败，未获取到Session")
            }
        } else {
            LoginResult.Error("登录失败，状态码: ${response.code}")
        }
    }

    sealed class LoginResult {
        data class Success(val cookies: List<String>) : LoginResult()
        data class Error(val message: String) : LoginResult()
    }
}
