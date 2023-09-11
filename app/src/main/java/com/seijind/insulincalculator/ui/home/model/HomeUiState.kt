package com.seijind.insulincalculator.ui.home.model

import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

data class HomeUiState(
    val foods: MutableList<Food> = mutableListOf(),
    val divider: MutableState<String> = mutableStateOf(""),
    val gi: MutableState<String> = mutableStateOf(""),
    val totalCarbs: MutableDoubleState = mutableDoubleStateOf(0.0),
    val result: MutableState<String> = mutableStateOf("")
)

data class Food(
    val name: MutableState<String> = mutableStateOf(""),
    val carbs: MutableDoubleState = mutableDoubleStateOf(0.0),
    val grams: MutableState<String> = mutableStateOf(""),
)