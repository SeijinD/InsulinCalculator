package com.seijind.insulincalculator.ui.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
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
                foods = foods
            )
        }
    }

    fun onResultClicked() {
        val totalCarbs = calculateTotalCarbs()
        val gi = uiState.value?.gi?.value?.toInt() ?: 0
        val divider = uiState.value?.divider?.value?.toInt() ?: 0

        val result = calculateResult(totalCarbs, gi, divider)
        updateUiState(result)
    }

    private fun calculateTotalCarbs(): Double {
        var totalCarbs = uiState.value?.totalCarbs?.doubleValue ?: 0.0
        uiState.value?.selectedFoods?.forEach { food ->
            totalCarbs += food.carbs.doubleValue * food.grams.value.toInt()
        }
        return totalCarbs
    }

    private fun calculateResult(totalCarbs: Double, gi: Int, divider: Int): Double {
        return when (gi) {
            in 0..59 -> customRound(totalCarbs / divider) - 0.5
            in 60..69, in 70..179 -> customRound(totalCarbs / divider)
            in 180..239 -> customRound(totalCarbs / divider) + 0.5
            in 240..299 -> customRound(totalCarbs / divider) + 1.0
            in 300..359 -> customRound(totalCarbs / divider) + 1.5
            in 360..Int.MAX_VALUE -> customRound(totalCarbs / divider) + 2.0
            else -> 0.0
        }
    }

    private fun updateUiState(result: Double) {
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