package com.seijind.insulincalculator.ui.home.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class HomeUiState(
    val result: MutableState<String> = mutableStateOf("")
)