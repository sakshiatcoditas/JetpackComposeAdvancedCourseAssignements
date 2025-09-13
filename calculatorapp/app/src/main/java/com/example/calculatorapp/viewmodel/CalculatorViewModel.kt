package com.example.calculatorapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var display = mutableStateOf("0")
        private set

    val display2 =  display.value
    private var firstNumber: String = ""
    private var operator: String? = null

    fun onNumberClick(number: String) {
        if (display.value == "0" || operator != null && display.value == firstNumber) {
            display.value = number
        } else {
            display.value += number
        }
    }

    fun onOperatorClick(op: String) {
        firstNumber = display.value
        operator = op
    }

    fun onEqualClick() {
        val secondNumber = display.value
        val result = try {
            when (operator) {
                "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                "×" -> firstNumber.toDouble() * secondNumber.toDouble()
                "÷" -> firstNumber.toDouble() / secondNumber.toDouble()
                else -> secondNumber.toDouble()
            }
        } catch (e: Exception) {
            0.0
        }
        //if number is
        display.value = if (result.isNaN()) "Error"
        else if (result % 1 == 0.0) result.toInt().toString()
        else result.toString()

    }

    fun onClear() {
        display.value = "0"
        firstNumber = ""
        operator = null
    }
}
