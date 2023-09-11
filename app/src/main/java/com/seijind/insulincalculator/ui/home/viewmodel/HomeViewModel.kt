package com.seijind.insulincalculator.ui.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.seijind.insulincalculator.ui.base.BaseViewModel
import com.seijind.insulincalculator.ui.home.model.Food
import com.seijind.insulincalculator.ui.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.floor

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel() {

    var uiState: MutableState<HomeUiState?> = mutableStateOf(HomeUiState())
        private set

    fun init() {
        launch {
            uiState.value = HomeUiState(
                foods = mutableListOf(
                    Food(
                        name = mutableStateOf("Apple"),
                        carbs = mutableDoubleStateOf(0.5),
                        grams = mutableStateOf("200")
                    ),
                    Food(
                        name = mutableStateOf("Watermelon"),
                        carbs = mutableDoubleStateOf(0.3),
                        grams = mutableStateOf("300")
                    )
                ),
                divider = mutableStateOf("10"),
                totalCarbs = mutableDoubleStateOf(50.0),
                gi = mutableStateOf("160"),
                result = mutableStateOf("")
            )
        }
    }

    fun onResultClicked() {
        for (food in uiState.value?.foods!!) {
            uiState.value?.totalCarbs?.doubleValue?.plus(food.carbs.doubleValue)
        }
        val totalCarbs = uiState.value?.totalCarbs?.doubleValue ?: 0.0
        val gi = uiState.value?.gi?.value?.toInt() ?: 0
        val divider = uiState.value?.divider?.value?.toInt() ?: 0

        val result: Double = when (gi) {
            in 0..70 -> customRound(totalCarbs / divider)
            in 71..180 -> customRound(totalCarbs / divider) + 0.5
            in 181..240 -> customRound(totalCarbs / divider) + 1
            in 241..300 -> customRound(totalCarbs / divider) + 1.5
            in 301..360 -> customRound(totalCarbs / divider) + 2
            else -> 0.0
        }
        uiState.value = uiState.value?.copy(result = mutableStateOf(result.toString()))
    }

    private fun customRound(number: Double): Double {
        val floorValue = floor(number)
        val decimalPart = number - floorValue

        return when {
            decimalPart >= 0.6 -> floorValue + 1.0
            decimalPart >= 0.4 -> floorValue + 0.5
            else -> floorValue
        }
    }
}