package com.example.workoutdemo.home


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _time = MutableStateFlow(0)
    val time = _time.asStateFlow()

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _calories = MutableStateFlow(0)
    val calories = _calories.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    fun toggleWorkout() {
        _isRunning.value = !_isRunning.value
    }

    fun increaseStats() {
        _time.value = _time.value + 1
        _steps.value = _steps.value + 3
        _calories.value = _calories.value + 1
    }
}
