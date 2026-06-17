package com.example.course.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.course.data.repository.UserRepository
import dagger.hilt.android.lifecycle.ViewModelScoped
import dagger.hilt.android.lifecycle.ViewModelInject
import kotlinx.coroutines.launch

@ViewModelScoped
class SettingsViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            userRepository.deleteCredential()
        }
    }
}
