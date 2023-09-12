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
                foods = listOf(
                    Food(
                        name = mutableStateOf("Apple"),
                        carbs = mutableDoubleStateOf(0.5),
                        grams = mutableStateOf("")
                    ),
                    Food(
                        name = mutableStateOf("Watermelon"),
                        carbs = mutableDoubleStateOf(0.3),
                        grams = mutableStateOf("")
                    )
                )
            )
        }
    }

    fun onResultClicked() {
        var totalCarbs = uiState.value?.totalCarbs?.doubleValue ?: 0.0
        uiState.value?.selectedFoods?.forEach { food ->
            totalCarbs += food.carbs.doubleValue * food.grams.value.toInt()
        }
        val gi = uiState.value?.gi?.value?.toInt() ?: 0
        val divider = uiState.value?.divider?.value?.toInt() ?: 0

        val result: Double = when (gi) {
            in 0..69 -> customRound(totalCarbs / divider)
            in 70..179 -> customRound(totalCarbs / divider)
            in 180..239 -> customRound(totalCarbs / divider) + 0.5
            in 240..299 -> customRound(totalCarbs / divider) + 1.0
            in 300..360 -> customRound(totalCarbs / divider) + 1.5
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