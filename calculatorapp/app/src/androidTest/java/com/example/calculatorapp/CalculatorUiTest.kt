package com.example.calculatorapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class CalculatorUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addition_works_correctly() {
        // Actions
        composeTestRule.onNodeWithTag("Button_2").performClick()
        composeTestRule.onNodeWithTag("Button_+").performClick()
        composeTestRule.onNodeWithTag("Button_3").performClick()
        composeTestRule.onNodeWithTag("Button_=").performClick()

        // Assertion
        composeTestRule.onNodeWithTag("CalculatorDisplay")
            .assertTextEquals("6")
    }

    @Test
    fun clear_resets_display() {
        composeTestRule.onNodeWithTag("Button_9").performClick()
        composeTestRule.onNodeWithTag("Button_C").performClick()

        composeTestRule.onNodeWithTag("CalculatorDisplay")
            .assertTextEquals("0")
    }

    @Test
    fun subtraction_works_correctly() {
        composeTestRule.onNodeWithTag("Button_7").performClick()
        composeTestRule.onNodeWithTag("Button_-").performClick()
        composeTestRule.onNodeWithTag("Button_2").performClick()
        composeTestRule.onNodeWithTag("Button_=").performClick()

        composeTestRule.onNodeWithTag("CalculatorDisplay")
            .assert(hasText("5")) // Using matcher
    }
}
