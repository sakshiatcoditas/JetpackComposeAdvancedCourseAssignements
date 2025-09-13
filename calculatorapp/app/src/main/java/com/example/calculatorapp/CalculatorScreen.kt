package com.example.calculatorapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(vm: CalculatorViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Display
        Text(
            text = vm.display.value,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag("CalculatorDisplay")
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Buttons grid
        val rows = listOf(
            listOf("7", "8", "9", "÷"),
            listOf("4", "5", "6", "×"),
            listOf("1", "2", "3", "-"),
            listOf("0", "C", "=", "+")
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            rows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    row.forEach { text ->
                        CalcButton(
                            text = text,
                            onClick = {
                                when (text) {
                                    "C" -> vm.onClear()
                                    "=" -> vm.onEqualClick()
                                    "+", "-", "×", "÷" -> vm.onOperatorClick(text)
                                    else -> vm.onNumberClick(text)
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .height(64.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalcButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(Color.LightGray)
            .clickable { onClick() }
            .testTag("Button_$text"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
