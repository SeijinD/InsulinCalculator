package com.seijind.insulincalculator.ui.home.model

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf

object Foods {
    val foods = listOf(
        Food(
            name = mutableStateOf("Apple"),
            carbs = mutableDoubleStateOf(0.5),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Watermelon"),
            carbs = mutableDoubleStateOf(0.3),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Egg"),
            carbs = mutableDoubleStateOf(0.5),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Watermelon Big"),
            carbs = mutableDoubleStateOf(0.3),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Apple 2"),
            carbs = mutableDoubleStateOf(0.5),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Watermelon Small"),
            carbs = mutableDoubleStateOf(0.3),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Potatoes"),
            carbs = mutableDoubleStateOf(0.5),
            grams = mutableStateOf("")
        ),
        Food(
            name = mutableStateOf("Potatoes with Cheese"),
            carbs = mutableDoubleStateOf(0.3),
            grams = mutableStateOf("")
        )
    )
}