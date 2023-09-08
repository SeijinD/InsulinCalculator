package com.seijind.insulincalculator.ui.home.model

import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

data class HomeUiState(
    val foods: MutableList<Food> = mutableListOf(),
    val divider: MutableIntState = mutableIntStateOf(0),
    val totalCarbs: MutableDoubleState = mutableDoubleStateOf(0.0),
    val gi: MutableIntState = mutableIntStateOf(0),
    val result: MutableState<String> = mutableStateOf("")
)

data class Food(
    val name: String = "",
    val carbs: Double = 0.0,
    val grams: MutableIntState = mutableIntStateOf(0)
)